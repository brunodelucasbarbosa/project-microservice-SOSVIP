package br.com.sosvip.apigerenciamentopedidos.controller;

import br.com.sosvip.apigerenciamentopedidos.aws.ses.SESService;
import br.com.sosvip.apigerenciamentopedidos.exception.InternalServerErrorException;
import br.com.sosvip.apigerenciamentopedidos.exception.ResourceNotFoundException;
import br.com.sosvip.apigerenciamentopedidos.model.Order;
import br.com.sosvip.apigerenciamentopedidos.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public IOrderService service;

    @GetMapping
    public ResponseEntity<?> getAllOrders(Pageable pageable){
        try {
            return ResponseEntity.ok(service.getAllOrders(pageable));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }  

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOrderById(id));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<Order>> getAllOrdersByClientId(@PathVariable Long clientId, Pageable pageable) {
        try {
            return ResponseEntity.ok(service.getAllOrderByClientId(clientId, pageable));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @SqsListener("grupo1-sosvip")
    public void receiveMessage(String stringJson) {
        service.createOrder(stringJson);
    }


}
