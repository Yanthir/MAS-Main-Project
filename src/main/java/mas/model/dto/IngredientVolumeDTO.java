package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class IngredientVolumeDTO extends AbstractDTO {
    private String ingredientId;
    private String recipeId;
    private BigDecimal volume;
}
