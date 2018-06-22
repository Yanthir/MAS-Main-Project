package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.IngredientVolumeDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Ingredient.class, name = AssociationNames.ASSOC_INGREDIENT_VOLUME_INGREDIENT, cardinality = 1)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_INGREDIENT_VOLUME_RECIPE, cardinality = 1)
public class IngredientVolume extends ExtendedBusinessObject {
    private BigDecimal volume;

    public IngredientVolume(IngredientVolumeDTO ingredientVolumeDTO) {
        super(ingredientVolumeDTO);
        setVolume(ingredientVolumeDTO.getVolume());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("IngredientVolume business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof IngredientVolumeDTO)) {
            throw new RuntimeException("IngredientVolume business object failed to create: DTO object type mismatch");
        }
        String ingredientId = ((IngredientVolumeDTO) abstractDTO).getIngredientId();
        Ingredient ingredient = (Ingredient) getById(Ingredient.class, ingredientId);
        if (ingredient != null) {
            try {
                link(
                        AssociationNames.ASSOC_INGREDIENT_VOLUME_INGREDIENT,
                        AssociationNames.ASSOC_INGREDIENT_INGREDIENT_VOLUMES,
                        ingredient
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String recipeId = ((IngredientVolumeDTO) abstractDTO).getRecipeId();
        Recipe recipe = (Recipe) getById(Recipe.class, recipeId);
        if (recipe != null) {
            try {
                link(
                        AssociationNames.ASSOC_INGREDIENT_VOLUME_RECIPE,
                        AssociationNames.ASSOC_RECIPE_INGREDIENT_VOLUMES,
                        recipe
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }
}
