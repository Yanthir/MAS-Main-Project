package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.*;
import mas.model.constants.AssociationNames;
import mas.model.constants.Role;
import mas.model.dto.AbstractDTO;
import mas.model.dto.BeverageDTO;
import mas.model.dto.EmployeeDTO;
import mas.util.RoleUtil;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Report.class, name = AssociationNames.ASSOC_CONTROLLER_REPORTS)
public class Employee extends Person {
    String bankAccountNumber;
    String phoneNumber;
    @Setter(AccessLevel.PRIVATE)
    Date employmentDate;
    List<Role> roles;

    public Employee(EmployeeDTO employeeDTO) {
        super(employeeDTO);
        setBankAccountNumber(employeeDTO.getBankAccountNumber());
        setPhoneNumber(employeeDTO.getPhoneNumber());
        setRoles(RoleUtil.getRolesByIds(employeeDTO.getRoleIds()));
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        // Do nothing
    }
}
