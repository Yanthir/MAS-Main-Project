package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.ClientDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Order.class, name = AssociationNames.ASSOC_CLIENT_ORDERS)
@Association(targetClass = Subscription.class, name = AssociationNames.ASSOC_CLIENT_SUBSCRIPTIONS)
public class Client extends Person {
    private String emailAddress;

    public Client(ClientDTO clientDTO) {
        super(clientDTO);
        setEmailAddress(clientDTO.getEmailAddress());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        // Do nothing
    }
}
