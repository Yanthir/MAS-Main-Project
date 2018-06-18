package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.math.BigDecimal;

@Data
@Association(targetClass = IngredientVolume.class, name = AssociationNames.ASSOC_INGREDIENT_VOLUMES_FOR_RECIPES)
@Association(targetClass = Supplier.class, name = AssociationNames.ASSOC_INGREDIENT_SUPPLIER, cardinality = 1)
public class Ingredient extends BusinessObject {
    String name;
    BigDecimal pricePerKilo;
    BigDecimal yieldPerKilo;

    public Ingredient() {
        super();
    }
}
