package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TemplateQuantityDTO extends AbstractDTO {
    private String templateId;
    private String setId;
    private int quantity;
}
