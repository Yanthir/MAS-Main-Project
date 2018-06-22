package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.IngredientDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = IngredientVolume.class, name = AssociationNames.ASSOC_INGREDIENT_INGREDIENT_VOLUMES)
@Association(targetClass = Supplier.class, name = AssociationNames.ASSOC_INGREDIENT_SUPPLIER, cardinality = 1)
public class Ingredient extends ExtendedBusinessObject {
    private String name;
    private BigDecimal pricePerKilo;
    private BigDecimal yieldPerKilo;

    public Ingredient(IngredientDTO ingredientDTO) {
        super(ingredientDTO);
        setName(ingredientDTO.getName());
        setPricePerKilo(ingredientDTO.getPricePerKilo());
        setYieldPerKilo(ingredientDTO.getYieldPerKilo());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Beverage business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof IngredientDTO)) {
            throw new RuntimeException("Beverage business object failed to create: DTO object type mismatch");
        }
        String supplierId = ((IngredientDTO) abstractDTO).getSupplierId();
        Supplier supplier = (Supplier) getById(Supplier.class, supplierId);
        if (supplier != null) {
            try{
                link(
                        AssociationNames.ASSOC_INGREDIENT_SUPPLIER,
                        AssociationNames.ASSOC_SUPPLIER_INGREDIENTS,
                        supplier
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }
}
