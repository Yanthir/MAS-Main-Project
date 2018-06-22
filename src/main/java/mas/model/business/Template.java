package mas.model.business;

import associations.Association;
import associations.AssociationException;
import associations.LinkingException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.exception.BusinessException;
import mas.model.constants.AssociationNames;
import mas.model.dto.AbstractDTO;
import mas.model.dto.SubscriptionDTO;
import mas.model.dto.TemplateDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Vessel.class, name = AssociationNames.ASSOC_TEMPLATE_VESSEL, cardinality = 1)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_TEMPLATE_RECIPE, cardinality = 1)
@Association(targetClass = TemplateQuantity.class, name = AssociationNames.ASSOC_TEMPLATE_TEMPLATE_QUANTITIES)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_TEMPLATE_BEVERAGES)
public class Template extends ExtendedBusinessObject {
    private String name;
    private BigDecimal price;

    public Template(TemplateDTO templateDTO) {
        super(templateDTO);
        setName(templateDTO.getName());
        setPrice(templateDTO.getPrice());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Template business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof TemplateDTO)) {
            throw new RuntimeException("Template business object failed to create: DTO object type mismatch");
        }
        String recipeId = ((TemplateDTO) abstractDTO).getRecipeId();
        Recipe recipe = (Recipe) getById(Recipe.class, recipeId);
        if (recipe != null) {
            try {
                link(
                        AssociationNames.ASSOC_TEMPLATE_RECIPE,
                        AssociationNames.ASSOC_RECIPE_TEMPLATES,
                        recipe
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
        String vesselId = ((TemplateDTO) abstractDTO).getVesselId();
        Vessel vessel = (Vessel) getById(Vessel.class, vesselId);
        if (vessel != null) {
            try {
                link(
                        AssociationNames.ASSOC_TEMPLATE_VESSEL,
                        AssociationNames.ASSOC_VESSEL_TEMPLATES,
                        vessel,
                        vesselId
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }

    public BigDecimal getProductionPrice() {
        BigDecimal productionPrice = BigDecimal.ZERO;
        try{
            Recipe recipe = (Recipe) getLinkedObjects(AssociationNames.ASSOC_TEMPLATE_RECIPE)[0];
            Vessel vessel = (Vessel) getLinkedObjects(AssociationNames.ASSOC_TEMPLATE_VESSEL)[0];

            BigDecimal recipePriceForTemplate = recipe.getProductionPrice()
                    .divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_UP)
                    .multiply(vessel.getVolume());
            productionPrice = productionPrice
                    .add(recipePriceForTemplate)
                    .add(vessel.getPrice());
        } catch (AssociationException e) {
            throw new RuntimeException(e);
        }
        return productionPrice;
    }
}
