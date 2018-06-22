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
public class BatchDTO extends AbstractDTO {
    private String recipeId;
    private String statusId;
    private Date createDate;
    private Date expirationDate;
    private BigDecimal volume;
    private BigDecimal availableVolume;

    public BatchDTO(Batch batch) {
        setId(batch.getId());
        setStatusId(StatusUtil.getStatusId(batch.getStatus()));
        setCreateDate(batch.getCreateDate());
        setExpirationDate(batch.getExpirationDate());
        setVolume(batch.getVolume());
        setAvailableVolume(batch.getAvailableVolume());
    }
}

