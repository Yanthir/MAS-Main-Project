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
import mas.model.dto.OrderDTO;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Client.class, name = AssociationNames.ASSOC_ORDER_CLIENT, cardinality = 1)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_ORDER_BEVERAGES)
public class Order extends ExtendedBusinessObject {
    @Setter(AccessLevel.PRIVATE)
    private Date submissionDate;
    private Date deliveryDate;

    public Order(OrderDTO orderDTO) {
        super(orderDTO);
        setDeliveryDate(orderDTO.getDeliveryDate());
        setSubmissionDate(orderDTO.getSubmissionDate());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Order business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof OrderDTO)) {
            throw new RuntimeException("Order business object failed to create: DTO object type mismatch");
        }
        String clientId = ((OrderDTO) abstractDTO).getClientId();
        Client client = (Client) getById(Client.class, clientId);
        if (client != null) {
            try {
                link(
                        AssociationNames.ASSOC_ORDER_CLIENT,
                        AssociationNames.ASSOC_CLIENT_ORDERS,
                        client
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }

    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.ZERO;

        try {
            List<Beverage> beverages = Arrays.stream(getLinkedObjects(AssociationNames.ASSOC_ORDER_BEVERAGES))
                    .map(Beverage.class::cast)
                    .collect(Collectors.toList());
            for (Beverage beverage : beverages) {
                Template template = (Template) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_TEMPLATE)[0];
                BigDecimal beveragePrice = template.getPrice();
                price = beveragePrice.add(price);
            }
        } catch (AssociationException e) {
            throw new RuntimeException(e);
        }

        return price;
    }

    public String getSubmissionDateFormatted() {
        return DateFormatUtils.format(getSubmissionDate(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getDeliveryDateFormatted() {
        return getDeliveryDate() != null ? DateFormatUtils.format(getDeliveryDate(), "yyyy-MM-dd HH:mm:ss") : "--";
    }
}
