package mas.model;

import associations.BusinessObject;
import lombok.Data;

@Data
public class Person extends BusinessObject {
    long id;
    String name;
    String surname;
    String address;

    public Person() {
        super();
    }
}
