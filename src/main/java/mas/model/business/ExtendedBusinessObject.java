package mas.model.business;

import associations.BusinessObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import mas.model.dto.AbstractDTO;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class ExtendedBusinessObject extends BusinessObject {
    private static Map<Class, Map<String, ExtendedBusinessObject>> extents = new HashMap<>();

    @Setter(AccessLevel.PRIVATE)
    private String id;

    public ExtendedBusinessObject(AbstractDTO abstractDTO) {
        super();
        setId(abstractDTO.getId());
        addToExtent();
    }

    private void addToExtent() {
        Class objectClass = this.getClass();
        if(!extents.containsKey(objectClass)) {
            extents.put(objectClass, new HashMap<>());
        }
        extents.get(objectClass).put(getId(), this);
    }

    public abstract void createLinks(AbstractDTO abstractDTO);

    public static ExtendedBusinessObject getById(Class objectClass, String id) {
        if(extents.containsKey(objectClass)) {
            return extents.get(objectClass).get(id);
        }
        return null;
    }

    public static ExtendedBusinessObject[] getAll(Class objectClass) {
        if(extents.containsKey(objectClass)) {
            return extents.get(objectClass).values().toArray(new ExtendedBusinessObject[0]);
        }
        return new ExtendedBusinessObject[0];
    }
}
