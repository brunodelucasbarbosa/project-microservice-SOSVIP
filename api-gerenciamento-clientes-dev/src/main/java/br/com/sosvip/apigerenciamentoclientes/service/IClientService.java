package br.com.sosvip.apigerenciamentoclientes.service;

import br.com.sosvip.apigerenciamentoclientes.dto.OrderDTO;
import br.com.sosvip.apigerenciamentoclientes.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientService {

    Page<Client> getAllClients(Pageable pageable);
    Client getClientById(Long id);
    Client createClient(Client newClient);
    Client updateClient(Client client);
    void deleteClient(Long id);
    Client getByEmail(String email);
    Client getByCpf(String cpf);
    String createOrder(OrderDTO newOrder);

}
