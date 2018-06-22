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
import mas.model.constants.Status;
import mas.model.dto.AbstractDTO;
import mas.model.dto.BatchDTO;
import mas.model.dto.IngredientDTO;
import mas.util.StatusUtil;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Association(targetClass = Report.class, name = AssociationNames.ASSOC_BATCH_REPORTS)
@Association(targetClass = Recipe.class, name = AssociationNames.ASSOC_BATCH_RECIPE, cardinality = 1)
@Association(targetClass = Beverage.class, name = AssociationNames.ASSOC_BATCH_BEVERAGES)
public class Batch extends ExtendedBusinessObject {
    @Setter(AccessLevel.PRIVATE)
    private Date createDate;
    @Setter(AccessLevel.PRIVATE)
    private Date expirationDate;
    private Status status;
    private BigDecimal volume;

    public Batch(BatchDTO batchDTO) {
        super(batchDTO);
        setCreateDate(batchDTO.getCreateDate());
        setExpirationDate(batchDTO.getExpirationDate());
        setStatus(StatusUtil.getStatusById(batchDTO.getStatusId()));
        setVolume(batchDTO.getVolume());
    }

    @Override
    public void createLinks(AbstractDTO abstractDTO) {
        if(!abstractDTO.getId().equals(getId())) {
            throw new RuntimeException("Batch business object failed to create: ID mismatch");
        }
        if(!(abstractDTO instanceof BatchDTO)) {
            throw new RuntimeException("Batch business object failed to create: DTO object type mismatch");
        }
        String recipeId = ((BatchDTO) abstractDTO).getRecipeId();
        Recipe recipe = (Recipe) getById(Recipe.class, recipeId);
        if (recipe != null) {
            try{
                link(
                        AssociationNames.ASSOC_BATCH_RECIPE,
                        AssociationNames.ASSOC_RECIPE_BATCHES,
                        recipe
                );
            } catch (AssociationException | LinkingException e) {
                throw new BusinessException(e);
            }
        }
    }

    public BigDecimal getAvailableVolume() {
        BigDecimal availableVolume = volume;
        try {
            Beverage[] beverages = (Beverage[]) getLinkedObjects(AssociationNames.ASSOC_BATCH_BEVERAGES);
            for (Beverage beverage : beverages) {
                Template template = (Template) beverage.getLinkedObjects(AssociationNames.ASSOC_BEVERAGE_TEMPLATE)[0];
                Vessel vessel = (Vessel) template.getLinkedObjects(AssociationNames.ASSOC_TEMPLATE_VESSEL)[0];
                availableVolume = availableVolume.subtract(vessel.getVolume());
            }
        } catch (AssociationException e) {
            throw new RuntimeException(e);
        }
        return availableVolume;
    }
}

