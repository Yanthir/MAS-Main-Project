package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.ReportDTO;
import mas.model.dto.SetDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = TemplateQuantity.class, name = AssociationNames.ASSOC_SET_TEMPLATE_QUANTITIES)
@Association(targetClass = Subscription.class, name = AssociationNames.ASSOC_SET_SUBSCRIPTIONS)
public class Set extends ExtendedBusinessObject {
    private String name;
    private String description;
    private BigDecimal price;

    public Set(SetDTO setDTO) {
        super(setDTO);
        setDescription(setDTO.getDescription());
        setName(setDTO.getName());
        price = setDTO.getPrice();
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        // Do nothing
    }

    public void setPrice(BigDecimal price) throws AssociationException {
        List<TemplateQuantity> templateQuantities = Arrays.stream(getLinkedObjects(AssociationNames.ASSOC_SET_TEMPLATE_QUANTITIES))
                .map(TemplateQuantity.class::cast)
                .collect(Collectors.toList());
        BigDecimal productionPrice = BigDecimal.ZERO;

        for(TemplateQuantity templateQuantity : templateQuantities) {
            Template template = (Template) templateQuantity.getLinkedObjects(AssociationNames.ASSOC_TEMPLATE_QUANTITY_TEMPLATE)[0];
            productionPrice = productionPrice
                    .add(template.getProductionPrice())
                    .multiply(BigDecimal.valueOf(templateQuantity.getQuantity()));
        }
        productionPrice = productionPrice.multiply(BigDecimal.valueOf(1.5));

        if(productionPrice.compareTo(price) < 0) {
            price = productionPrice;
        }

        this.price = price;
    }
}
