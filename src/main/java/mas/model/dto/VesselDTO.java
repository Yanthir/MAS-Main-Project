package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class VesselDTO extends AbstractDTO {
    private String supplierId;
    private String name;
    private BigDecimal volume;
    private BigDecimal price;
    private int stock;
}
