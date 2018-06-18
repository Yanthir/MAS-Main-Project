package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.util.Date;

@Data
@Association(targetClass = Batch.class, name = AssociationNames.ASSOC_BEVERAGE_BATCH, cardinality = 1)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_BEVERAGE_TEMPLATE, cardinality = 1)
@Association(targetClass = Order.class, name = AssociationNames.ASSOC_BEVERAGE_ORDER, cardinality = 1)
public class Beverage extends BusinessObject {
    long id;
    Date createDate;

    public Beverage() {
        super();
    }
}
