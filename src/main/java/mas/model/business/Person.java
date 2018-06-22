package mas.model.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mas.model.dto.PersonDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Person extends ExtendedBusinessObject {
    private String name;
    private String surname;
    private String address;

    public Person(PersonDTO personDTO) {
        super(personDTO);
        setName(personDTO.getName());
        setSurname(personDTO.getSurname());
        setAddress(personDTO.getAddress());
    }
}
