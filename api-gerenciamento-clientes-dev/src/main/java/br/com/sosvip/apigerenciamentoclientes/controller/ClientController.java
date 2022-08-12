package br.com.sosvip.apigerenciamentoclientes.controller;

import br.com.sosvip.apigerenciamentoclientes.dto.OrderDTO;
import br.com.sosvip.apigerenciamentoclientes.exception.BadRequestException;
import br.com.sosvip.apigerenciamentoclientes.exception.InternalServerErrorException;
import br.com.sosvip.apigerenciamentoclientes.exception.ResourceNotFoundException;
import br.com.sosvip.apigerenciamentoclientes.model.Client;
import br.com.sosvip.apigerenciamentoclientes.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    public IClientService service;

    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(Pageable pageable){
        try {
            return ResponseEntity.ok(service.getAllClients(pageable));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getClientById(id));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client newClient){
        try {
            return ResponseEntity.ok(service.createClient(newClient));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
             throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,@Valid @RequestBody Client client) {
        client.setId(id);
        try {
            return ResponseEntity.ok(service.updateClient(client));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            service.deleteClient(id);
            return ResponseEntity.accepted().body("Cliente excluído com sucesso!");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO newOrder){
        try {
            return ResponseEntity.ok(service.createOrder(newOrder));
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
