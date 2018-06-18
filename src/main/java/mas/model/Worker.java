package mas.model;

import associations.Association;
import lombok.Data;
import mas.model.constants.AssociationNames;
import mas.model.constants.Role;

import java.util.Date;
import java.util.List;

@Data
@Association(targetClass = Report.class, name = AssociationNames.ASSOC_CONTROLLER_REPORTS)
public class Worker extends Person {
    String bankAccountNumber;
    String phoneNumber;
    Date employmentDate;
    List<Role> roles;

    public Worker() {
        super();
    }
}
