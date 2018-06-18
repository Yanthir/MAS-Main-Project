package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.util.Calendar;
import java.util.Date;

@Data
@Association(targetClass = Set.class, name = AssociationNames.ASSOC_SUBSRIPTION_SET, cardinality = 1)
@Association(targetClass = Client.class, name = AssociationNames.ASSOC_SUBSRIPTION_CLIENT, cardinality = 1)
public class Subscription extends BusinessObject{
    int cycleLength;
    int cycleCount;
    Date startDate;

    public Subscription() {
        super();
    }

    public Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, cycleCount * cycleLength);
        return calendar.getTime();
    }
}
