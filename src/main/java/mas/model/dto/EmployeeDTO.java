package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EmployeeDTO extends PersonDTO {
    String bankAccountNumber;
    String phoneNumber;
    Date employmentDate;
    List<String> roleIds;
}
