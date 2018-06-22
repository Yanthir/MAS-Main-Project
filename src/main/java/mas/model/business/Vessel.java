package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.TemplateQuantityDTO;
import mas.model.dto.VesselDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Supplier.class, name = AssociationNames.ASSOC_VESSEL_SUPPLIER, cardinality = 1)
@Association(targetClass = Template.class, name = AssociationNames.ASSOC_VESSEL_TEMPLATES)
public class Vessel extends ExtendedBusinessObject {
    private String name;
    private BigDecimal volume;
    private BigDecimal price;
    private int stock;

    public Vessel(VesselDTO vesselDTO) {
        super(vesselDTO);
        setName(vesselDTO.getName());
        setPrice(vesselDTO.getPrice());
        setStock(vesselDTO.getStock());
        setVolume(vesselDTO.getVolume());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Vessel business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof VesselDTO)) {
            throw new RuntimeException("Vessel business object failed to create: DTO object type mismatch");
        }
        String supplierId = ((VesselDTO) abstractDTO).getSupplierId();
        Supplier supplier = (Supplier) getById(Supplier.class, supplierId);
        if (supplier != null) {
            try {
                link(
                        AssociationNames.ASSOC_VESSEL_SUPPLIER,
                        AssociationNames.ASSOC_SUPPLIER_VESSELS,
                        supplier
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }
}
