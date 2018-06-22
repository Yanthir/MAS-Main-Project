package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SubscriptionDTO extends AbstractDTO {
    private String setId;
    private String clientId;
    private int cycleLength;
    private int cycleCount;
    private Date startDate;
    private Date endDate;
}
