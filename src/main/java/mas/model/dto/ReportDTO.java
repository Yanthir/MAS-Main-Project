package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ReportDTO extends AbstractDTO {
    private String employeeId;
    private String batchId;
    private String description;
    private Date createDate;
}
