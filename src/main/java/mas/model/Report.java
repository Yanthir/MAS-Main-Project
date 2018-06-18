package mas.model;

import associations.Association;
import associations.BusinessObject;
import lombok.Data;
import mas.model.constants.AssociationNames;

import java.util.Date;

@Data
@Association(targetClass = Batch.class, name = AssociationNames.ASSOC_REPORT_BATCH, cardinality = 1)
@Association(targetClass = Report.class, name = AssociationNames.ASSOC_REPORT_CONTROLLER, cardinality = 1)
public class Report extends BusinessObject {
    String description;
    Date createDate;

    public Report() {
        super();
    }
}
