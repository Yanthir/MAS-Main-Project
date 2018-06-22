package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SupplierDTO extends AbstractDTO {
    private String name;
    private String address;
    private String phoneNumber;
}
