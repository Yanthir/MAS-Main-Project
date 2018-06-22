package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class IngredientDTO extends AbstractDTO {
    private String supplierId;
    private String name;
    private BigDecimal pricePerKilo;
    private BigDecimal yieldPerKilo;
}
