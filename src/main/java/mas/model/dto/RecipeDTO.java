package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RecipeDTO extends AbstractDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
