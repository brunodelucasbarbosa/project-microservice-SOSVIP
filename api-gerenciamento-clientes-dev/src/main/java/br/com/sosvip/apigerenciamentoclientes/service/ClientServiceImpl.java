package br.com.sosvip.apigerenciamentoclientes.service;

import br.com.sosvip.apigerenciamentoclientes.dao.ClientDAO;
import br.com.sosvip.apigerenciamentoclientes.dto.OrderDTO;
import br.com.sosvip.apigerenciamentoclientes.dto.OrderDTOSqsRequest;
import br.com.sosvip.apigerenciamentoclientes.exception.BadRequestException;
import br.com.sosvip.apigerenciamentoclientes.exception.ResourceNotFoundException;
import br.com.sosvip.apigerenciamentoclientes.model.Client;
import br.com.sosvip.apigerenciamentoclientes.aws.sqs.services.SQSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public Page<Client> getAllClients(Pageable pageable) {

        return clientDAO.findAll(pageable);
    }

    @Override
    public Client getClientById(Long id) {
        return clientDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
    }

    @Override
    public Client createClient(Client newClient) {
        Client client = new Client();
        client.setName(newClient.getName());
        client.setCpf(newClient.getCpf());
        client.setPhone(newClient.getPhone());
        client.setBirthdate(newClient.getBirthdate());
        client.setEmail(newClient.getEmail());

        if (getByEmail(client.getEmail()) != null) {
            throw new BadRequestException("E-mail já cadastrado!");
        }

        if (getByCpf(client.getCpf()) != null) {
            throw new BadRequestException("CPF já cadastrado!");
        }

        return clientDAO.save(client);
    }

    @Override
    public Client updateClient(Client client) {

        Client existingClient = clientDAO.findById(client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        Client existingEmailClient = clientDAO.findByEmail(client.getEmail());
        if (existingEmailClient != null && existingEmailClient.getId() != existingClient.getId()) {
            throw new BadRequestException("E-mail já cadastrado!");
        }

        Client existingCpfClient = clientDAO.findByCpf(client.getCpf());
        if (existingCpfClient != null && existingCpfClient.getId() != existingClient.getId()) {
            throw new BadRequestException("CPF já cadastrado!");
        }

        return clientDAO.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client existingClient = clientDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        clientDAO.deleteById(id);
    }

    @Override
    public Client getByEmail(String email) {

        return clientDAO.findByEmail(email);
    }

    @Override
    public Client getByCpf(String cpf) {

        return clientDAO.findByCpf(cpf);
    }

    @Override
    public String createOrder(OrderDTO newOrder) {
        Client existingClient = clientDAO.findById(newOrder.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        OrderDTOSqsRequest orderSqs = new OrderDTOSqsRequest();
        orderSqs.setClientId(newOrder.getClientId());
        orderSqs.setTotalAmount(newOrder.getTotalAmount());
        orderSqs.setDescription(newOrder.getDescription());
        orderSqs.setDate(newOrder.getDate());
        orderSqs.setClientEmail(existingClient.getEmail());

        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(orderSqs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SQSService.sendMessage(json);

        return "Pedido pendente! Aguarde a confirmação por e-mail.";
    }
}

