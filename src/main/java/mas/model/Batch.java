package mas.model;

import associations.Association;
import associations.AssociationException;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;
import mas.model.constants.Status;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Association(targetClass = Report.class, name = AssociationNames.ASSOC_BATCH_REPORTS)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_BATCH_RECIPE, cardinality = 1)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_BATCH_BEVERAGES)
public class Batch extends BusinessObject {
    String id;
    Date createDate;
    BigDecimal volume;
    BigDecimal availableVolume;
    Status status;
    Date expirationDate;

    public Batch() {
        super();
    }

    public BigDecimal getAvailableVolume() throws AssociationException {
        if(availableVolume == null) {
            availableVolume = volume;

            Beverage[] beverages = (Beverage[]) getLinkedObjects(AssociationNames.ASSOC_BATCH_BEVERAGES);
            for(Beverage beverage : beverages) {
                Template template = (Template) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_TEMPLATE)[0];
                BusinessObject vessel = template.getLinkedObjects(AssociationNames.ASSOC_TEMPLATE_VESSEL)[0];
                volume.subtract(vessel.getVolume());
            }
        }
        return availableVolume;
    }
}

