package br.com.sosvip.apigerenciamentoclientes.ClientServiceTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sosvip.apigerenciamentoclientes.dao.ClientDAO;
import br.com.sosvip.apigerenciamentoclientes.model.Client;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void getAllClientTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client1 = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client1);

        Client client2 = new Client(
                "Cliente2",
                "12345678910",
                "11999998889",
                new Timestamp(date.getTime()),
                "cliente2@email.com");

        clientDAO.save(client2);

        List<Client> clients = new ArrayList<Client>();
        for (Client client : clientDAO.findAll()) {
            clients.add(client);
        }

        Assertions.assertThat(clients.size()).isEqualTo(2);
    }

    @Test
    public void getClientByIdTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);
        var client2 = clientDAO.findById(client.getId());

        Assertions.assertThat(clientDAO.findById(client.getId())).isNotNull();
        Assertions.assertThat(client2.get().getId()).isEqualTo(client.getId());
    }

    @Test
    public void createClientTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);

        Assertions.assertThat(client.getName()).isEqualTo("Cliente1");
        Assertions.assertThat(client.getCpf()).isEqualTo("12345678900");
        Assertions.assertThat(client.getPhone()).isEqualTo("11999998888");
        Assertions.assertThat(client.getBirthdate()).isEqualTo(new Timestamp(date.getTime()));
        Assertions.assertThat(client.getEmail()).isEqualTo("cliente1@email.com");
    }

    @Test
    public void updateClientTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);
        var client2 = clientDAO.findById(client.getId());

        if (client2.get().getId() == client.getId()) {
            client.setName("Cliente11");
            clientDAO.save(client);
        }

        var client3 = clientDAO.findById(client.getId());

        Assertions.assertThat(client3.get().getName()).isEqualTo("Cliente11");
    }

    @Test
    public void deleteClientTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);
        var client2 = clientDAO.findById(client.getId());

        if (client2.get().getId() == 1) {
            clientDAO.deleteById(1L);
        }

        Assertions.assertThat(clientDAO.findById(1L)).isEmpty();
    }

    @Test
    public void getByEmailTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);
        var client2 = clientDAO.findByEmail("cliente1@email.com");

        Assertions.assertThat(client2.getEmail()).isEqualTo("cliente1@email.com");
    }

    @Test
    public void getByCpfTest() {

        Date date = new Date(1994 - 01 - 30);

        Client client = new Client(
                "Cliente1",
                "12345678900",
                "11999998888",
                new Timestamp(date.getTime()),
                "cliente1@email.com");

        clientDAO.save(client);
        var client2 = clientDAO.findByCpf("12345678900");

        Assertions.assertThat(client2.getCpf()).isEqualTo("12345678900");
    }
}
