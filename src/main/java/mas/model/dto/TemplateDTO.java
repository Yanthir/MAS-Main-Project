package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TemplateDTO extends AbstractDTO {
    private String recipeId;
    private String vesselId;
    private String name;
    private BigDecimal price;
}
