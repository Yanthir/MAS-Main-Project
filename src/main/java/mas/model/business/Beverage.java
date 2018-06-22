package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.CompositionException;
import associations.LinkingException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.BeverageDTO;
import mas.model.dto.TemplateDTO;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Batch.class, name = AssociationNames.ASSOC_BEVERAGE_BATCH, cardinality = 1)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_BEVERAGE_TEMPLATE, cardinality = 1)
@Association(targetClass = Order.class, name = AssociationNames.ASSOC_BEVERAGE_ORDER, cardinality = 1)
public class Beverage extends ExtendedBusinessObject {
    @Setter(AccessLevel.PRIVATE)
    private Date createDate;

    public Beverage(BeverageDTO beverageDTO) {
        super(beverageDTO);
        setCreateDate(beverageDTO.getCreateDate());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Beverage business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof BeverageDTO)) {
            throw new RuntimeException("Beverage business object failed to create: DTO object type mismatch");
        }
        String batchId = ((BeverageDTO) abstractDTO).getBatchId();
        Batch batch = (Batch) getById(Batch.class, batchId);
        if (batch != null) {
            try {
                link(
                        AssociationNames.ASSOC_BEVERAGE_BATCH,
                        AssociationNames.ASSOC_BATCH_BEVERAGES,
                        batch
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String templateId = ((BeverageDTO) abstractDTO).getBatchId();
        Template template = (Template) getById(Template.class, templateId);
        if (template != null) {
            try {
                link(
                        AssociationNames.ASSOC_BEVERAGE_TEMPLATE,
                        AssociationNames.ASSOC_TEMPLATE_BEVERAGES,
                        template
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String orderId = ((BeverageDTO) abstractDTO).getBatchId();
        Order order = (Order) getById(Order.class, orderId);
        if (order != null) {
            try {
                compose(
                        AssociationNames.ASSOC_BEVERAGE_ORDER,
                        AssociationNames.ASSOC_ORDER_BEVERAGES,
                        order
                );
            } catch (AssociationException | LinkingException | CompositionException e) {
                throw new BusinessException(e);
            }
        }
    }

    public Date getExpirationDate() throws AssociationException {
        Batch batch = (Batch) getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_BATCH)[0];
        return batch.getExpirationDate();
    }
}
