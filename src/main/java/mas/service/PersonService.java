package mas.service;

import mas.mapper.PersonMapper;
import mas.model.Person;

public class PersonService {
    public static String getGreetingForId(String id) {
        Person person = PersonMapper.getById(id);
        return "Hello, " + person.getName() + " " + person.getSurname() + "!";
    }
}
