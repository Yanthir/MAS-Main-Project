package mas.service;

import mas.mapper.ClientMapper;
import mas.mapper.PersonMapper;
import mas.model.business.Client;
import mas.model.business.ExtendedBusinessObject;
import mas.model.business.Person;
import mas.model.dto.ClientDTO;
import mas.model.dto.PersonDTO;

import java.util.List;

public class ClientService {
    public static List<ClientDTO> loadClients() {
        List<ClientDTO> clientDTOS = ClientMapper.selectAllClients();
        for(ClientDTO clientDTO : clientDTOS) {
            new Client(clientDTO);
        }
        return clientDTOS;
    }

    public static String getGreetingForId(String id) {
        Client client = (Client) ExtendedBusinessObject.getById(Client.class, id);
        if (client != null) {
            return "Hello, " + client.getName() + " " + client.getSurname() + "!";
        }
        return "Who are you?";
    }
}
