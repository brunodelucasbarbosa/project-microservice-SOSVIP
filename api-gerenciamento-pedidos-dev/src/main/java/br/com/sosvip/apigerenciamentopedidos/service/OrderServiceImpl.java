package br.com.sosvip.apigerenciamentopedidos.service;

import br.com.sosvip.apigerenciamentopedidos.aws.ses.SESService;
import br.com.sosvip.apigerenciamentopedidos.dao.OrderDAO;
import br.com.sosvip.apigerenciamentopedidos.dto.OrderDTOSqsRequest;
import br.com.sosvip.apigerenciamentopedidos.exception.BadRequestException;
import br.com.sosvip.apigerenciamentopedidos.exception.ResourceNotFoundException;
import br.com.sosvip.apigerenciamentopedidos.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private SESService sesService;

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderDAO.findAll(pageable);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    @Override
    public Page<Order> getAllOrderByClientId(Long clientId, Pageable pageable) {

        Page<Order> orderGroup = orderDAO.findAllByClientId(clientId, pageable);

        if (orderGroup.isEmpty()) {
            throw new ResourceNotFoundException("Não encontramos nenhum pedido para o cliente informado.");
        }

        return orderGroup;
    }

    public void createOrder(String orderStr) {
        OrderDTOSqsRequest orderSqs =  convertJsonToOrderSqs(orderStr);
        String clientEmail = orderSqs.getClientEmail();

        Order order = new Order();
        order.setClientId(orderSqs.getClientId());
        order.setTotalAmount(orderSqs.getTotalAmount());
        order.setDescription(orderSqs.getDescription());
        order.setDate(orderSqs.getDate());
        order.setStatus(false);

        Order savedOrder = orderDAO.save(order);

        Boolean response = sesService.sendMessage(clientEmail);

        if (response) {
            savedOrder.setStatus(true);
            orderDAO.save(savedOrder);
        }

    }

    public OrderDTOSqsRequest convertJsonToOrderSqs(String json){
        ObjectMapper mapper = new ObjectMapper();

        OrderDTOSqsRequest orderSqs = null;

        try {
            orderSqs = mapper.readValue(json, OrderDTOSqsRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return orderSqs;
    }
}
