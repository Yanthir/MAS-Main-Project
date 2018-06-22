package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BeverageDTO extends AbstractDTO {
    private String templateId;
    private String orderId;
    private String batchId;
    private Date createDate;
}
