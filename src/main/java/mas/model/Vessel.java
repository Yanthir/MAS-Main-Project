package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.math.BigDecimal;

@Data
@Association(targetClass = Supplier.class, name = AssociationNames.ASSOC_VESSEL_SUPPLIER, cardinality = 1)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_VESSEL_TEMPLATES)
public class Vessel extends BusinessObject {
    long id;
    String name;
    BigDecimal volume;
    BigDecimal price;
    int stock;

    public Vessel() {
        super();
    }
}
