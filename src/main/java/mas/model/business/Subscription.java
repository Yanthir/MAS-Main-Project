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
import mas.model.dto.SubscriptionDTO;

import java.util.Calendar;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Set.class, name = AssociationNames.ASSOC_SUBSCRIPTION_SET, cardinality = 1)
@Association(targetClass = Client.class, name = AssociationNames.ASSOC_SUBSCRIPTION_CLIENT, cardinality = 1)
public class Subscription extends ExtendedBusinessObject {
    private int cycleLength;
    private int cycleCount;
    @Setter(AccessLevel.PRIVATE)
    private Date startDate;

    public Subscription(SubscriptionDTO subscriptionDTO) {
        super(subscriptionDTO);
        setCycleCount(subscriptionDTO.getCycleCount());
        setCycleLength(subscriptionDTO.getCycleLength());
        setStartDate(subscriptionDTO.getStartDate());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Subscription business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof SubscriptionDTO)) {
            throw new RuntimeException("Subscription business object failed to create: DTO object type mismatch");
        }
        String clientId = ((SubscriptionDTO) abstractDTO).getClientId();
        Client client = (Client) getById(Client.class, clientId);
        if (client != null) {
            try {
                link(
                        AssociationNames.ASSOC_SUBSCRIPTION_CLIENT,
                        AssociationNames.ASSOC_CLIENT_SUBSCRIPTIONS,
                        client
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String setId = ((SubscriptionDTO) abstractDTO).getSetId();
        Set set = (Set) getById(Set.class, setId);
        if (set != null) {
            try {
                link(
                        AssociationNames.ASSOC_SUBSCRIPTION_SET,
                        AssociationNames.ASSOC_SET_SUBSCRIPTIONS,
                        set
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }

    public Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, cycleCount * cycleLength);
        return calendar.getTime();
    }
}
