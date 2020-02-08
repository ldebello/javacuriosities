package ar.com.javacuriosities.rs.repository;

import ar.com.javacuriosities.rs.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository {

    private static final Map<Integer, Client> clients = new HashMap<>();

    private static Integer lastID;

    static {
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("Cosme");
        client1.setAge(96);

        clients.put(client1.getId(), client1);

        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Pedro");
        client2.setAge(97);

        clients.put(client2.getId(), client2);

        Client client3 = new Client();
        client3.setId(3);
        client3.setName("Pablo");
        client3.setAge(98);

        clients.put(client3.getId(), client3);

        Client client4 = new Client();
        client4.setId(4);
        client4.setName("Sancho");
        client4.setAge(99);

        clients.put(client4.getId(), client4);

        lastID = clients.size();
    }

    public static Client getClient(Integer id) {
        return clients.get(id);
    }

    public static List<Client> getClients() {
        return new ArrayList<>(clients.values());
    }

    public static List<Client> getClientByName(String name) {
        List<Client> clients = new ArrayList<>();
        for (Map.Entry<Integer, Client> entry : ClientRepository.clients.entrySet()) {
            Client currentClient = entry.getValue();
            String clientName = currentClient.getName();
            if (clientName != null && clientName.contains(name)) {
                clients.add(currentClient);
            }
        }
        return clients;
    }

    public static Client saveClient(Client client) {
        lastID += 1;
        client.setId(lastID);
        clients.put(client.getId(), client);
        return client;
    }

    public static Client updateClient(Client client) {
        clients.put(client.getId(), client);
        return client;
    }

    public static void deleteClient(int id) {
        clients.remove(id);
    }
}
