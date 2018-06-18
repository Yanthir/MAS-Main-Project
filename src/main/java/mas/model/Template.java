package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.math.BigDecimal;

@Data
@Association(targetClass = Vessel.class, name = AssociationNames.ASSOC_TEMPLATE_VESSEL, cardinality = 1)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_TEMPLATE_RECIPE, cardinality = 1)
@Association(targetClass = Set.class, name = AssociationNames.ASSOC_TEMPLATE_SETS)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_TEMPLATE_BEVERAGES)
public class Template extends BusinessObject {
    long id;
    String name;
    BigDecimal price;

    public Template() {
        super();
    }
}
