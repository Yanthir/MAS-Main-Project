package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.SubscriptionDTO;
import mas.model.dto.SupplierDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Vessel.class, name = AssociationNames.ASSOC_SUPPLIER_VESSELS)
@Association(targetClass = Ingredient.class, name = AssociationNames.ASSOC_SUPPLIER_INGREDIENTS)
public class Supplier extends ExtendedBusinessObject {
    private String name;
    private String address;
    private String phoneNumber;

    public Supplier(SupplierDTO supplierDTO) {
        super(supplierDTO);
        setAddress(supplierDTO.getAddress());
        setName(supplierDTO.getName());
        setPhoneNumber(supplierDTO.getPhoneNumber());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        // do nothing
    }
}
