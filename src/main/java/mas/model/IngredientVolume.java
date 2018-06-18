package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.math.BigDecimal;

@Data
@Association(targetClass = Ingredient.class, name = AssociationNames.ASSOC_INGREDIENT_VOLUME_INGREDIENT, cardinality = 1)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_INGREDIENT_VOLUME_RECIPE, cardinality = 1)
public class IngredientVolume extends BusinessObject {
    BigDecimal volume;

    public IngredientVolume() {
        super();
    }
}
