package mas.model;

import associations.Association;
import associations.AssociationException;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Association(targetClass = Client.class, name = AssociationNames.ASSOC_ORDER_CLIENT, cardinality = 1)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_ORDER_BEVERAGES)
public class Order extends BusinessObject {
    long id;
    Date submissionDate;
    Date deliveryDate;

    public Order() {
        super();
    }

    public BigDecimal getPrice() throws AssociationException {
        BigDecimal price = BigDecimal.ZERO;

        Beverage[] beverages = (Beverage[]) getLinkedObjects(AssociationNames.ASSOC_ORDER_BEVERAGES);
        for(Beverage beverage : beverages) {
            Template template = (Template) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_TEMPLATE)[0];
            BigDecimal beveragePrice = template.getPrice();
            price = beveragePrice.add(price);
        }

        return price;
    }
}
