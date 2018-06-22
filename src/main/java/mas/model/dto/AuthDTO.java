package mas.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mas.model.business.Batch;
import mas.util.StatusUtil;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AuthDTO extends AbstractDTO {
    private String login;
    private String hash;
}

