package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderDTO extends AbstractDTO {
    private String clientId;
    private Date submissionDate;
    private Date deliveryDate;
    private BigDecimal price;
}
