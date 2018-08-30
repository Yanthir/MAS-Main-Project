package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.IngredientVolumeDTO;
import mas.model.dto.ReportDTO;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Batch.class, name = AssociationNames.ASSOC_REPORT_BATCH, cardinality = 1)
@Association(targetClass = Employee.class, name = AssociationNames.ASSOC_REPORT_CONTROLLER, cardinality = 1)
public class Report extends ExtendedBusinessObject {
    private String description;
    @Setter(AccessLevel.PRIVATE)
    private Date createDate;

    public Report(ReportDTO reportDTO) {
        super(reportDTO);
        setDescription(reportDTO.getDescription());
        setCreateDate(reportDTO.getCreateDate());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Report business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof ReportDTO)) {
            throw new RuntimeException("Report business object failed to create: DTO object type mismatch");
        }
        String batchId = ((ReportDTO) abstractDTO).getBatchId();
        Batch batch = (Batch) getById(Batch.class, batchId);
        if (batch != null) {
            try {
                link(
                        AssociationNames.ASSOC_REPORT_BATCH,
                        AssociationNames.ASSOC_BATCH_REPORTS,
                        batch
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String employeeId = ((ReportDTO) abstractDTO).getEmployeeId();
        Employee employee = (Employee) getById(Employee.class, employeeId);
        if (employee != null) {
            try {
                link(
                        AssociationNames.ASSOC_REPORT_CONTROLLER,
                        AssociationNames.ASSOC_CONTROLLER_REPORTS,
                        employee
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }

    public String getCreateDateFormatted() {
        return DateFormatUtils.format(getCreateDate(), "yyyy-MM-dd HH:mm:ss");
    }
}
