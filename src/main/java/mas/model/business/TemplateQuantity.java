package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.TemplateDTO;
import mas.model.dto.TemplateQuantityDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Set.class, name = AssociationNames.ASSOC_TEMPLATE_QUANTITY_SET, cardinality = 1)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_TEMPLATE_QUANTITY_TEMPLATE, cardinality = 1)
public class TemplateQuantity extends ExtendedBusinessObject {
    private int quantity;

    public TemplateQuantity(TemplateQuantityDTO templateQuantityDTO) {
        super(templateQuantityDTO);
        setQuantity(templateQuantityDTO.getQuantity());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("TemplateQuantity business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof TemplateQuantityDTO)) {
            throw new RuntimeException("TemplateQuantity business object failed to create: DTO object type mismatch");
        }
        String setId = ((TemplateQuantityDTO) abstractDTO).getSetId();
        Set set = (Set) getById(Set.class, setId);
        if (set != null) {
            try {
                link(
                        AssociationNames.ASSOC_TEMPLATE_QUANTITY_SET,
                        AssociationNames.ASSOC_SET_TEMPLATE_QUANTITIES,
                        set
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String templateId = ((TemplateQuantityDTO) abstractDTO).getTemplateId();
        Template template = (Template) getById(Template.class, templateId);
        if (template != null) {
            try {
                link(
                        AssociationNames.ASSOC_TEMPLATE_QUANTITY_TEMPLATE,
                        AssociationNames.ASSOC_TEMPLATE_TEMPLATE_QUANTITIES,
                        template
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }
}
