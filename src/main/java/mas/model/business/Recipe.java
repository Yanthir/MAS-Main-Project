package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.RecipeDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Batch.class, name = AssociationNames.ASSOC_RECIPE_BATCHES)
@Association(targetClass = IngredientVolume.class, name = AssociationNames.ASSOC_RECIPE_INGREDIENT_VOLUMES)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_RECIPE_TEMPLATES)
public class Recipe extends ExtendedBusinessObject {
    private String name;
    private String description;

    public Recipe(RecipeDTO recipeDTO) {
        super(recipeDTO);
        setDescription(recipeDTO.getDescription());
        setName(recipeDTO.getName());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        // Do nothing
    }

    public BigDecimal getProductionPrice() {
        BigDecimal productionPrice = BigDecimal.ZERO;
        try {
            List<IngredientVolume> volumes = Arrays.stream(getLinkedObjects(AssociationNames.ASSOC_RECIPE_INGREDIENT_VOLUMES))
                    .map(IngredientVolume.class::cast)
                    .collect(Collectors.toList());
            for (IngredientVolume volume : volumes) {
                Ingredient ingredient = (Ingredient) volume.getLinkedObjects(AssociationNames.ASSOC_INGREDIENT_VOLUME_INGREDIENT)[0];
                BigDecimal ingredientPrice = volume.getVolume()
                        .divide(ingredient.getYieldPerKilo(), 4, BigDecimal.ROUND_UP)
                        .multiply(ingredient.getPricePerKilo());
                productionPrice = productionPrice.add(ingredientPrice);
            }
            productionPrice = productionPrice.multiply(BigDecimal.valueOf(100));
        } catch (AssociationException e) {
            throw new RuntimeException(e);
        }
        return productionPrice;
    }
}
