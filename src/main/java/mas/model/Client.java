package mas.model;

import associations.Association;
import lombok.Data;
import mas.model.constants.AssociationNames;

@Data
@Association(targetClass = Order.class, name = AssociationNames.ASSOC_CLIENT_ORDER)
@Association(targetClass = Subscription.class, name = AssociationNames.ASSOC_CLIENT_SUBSCRIPTION)
public class Client extends Person {
    String emailAddress;

    public Client() {
        super();
    }
}
