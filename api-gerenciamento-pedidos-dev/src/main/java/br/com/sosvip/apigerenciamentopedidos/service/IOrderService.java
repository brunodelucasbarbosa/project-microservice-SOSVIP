package br.com.sosvip.apigerenciamentopedidos.service;

import java.util.Optional;

import br.com.sosvip.apigerenciamentopedidos.dto.OrderDTORequest;
import br.com.sosvip.apigerenciamentopedidos.dto.OrderDTOSqsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sosvip.apigerenciamentopedidos.model.Order;

public interface IOrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Order getOrderById(Long id);

    Page<Order> getAllOrderByClientId(Long clintId, Pageable pageable);

    void createOrder(String orderStr);

    OrderDTOSqsRequest convertJsonToOrderSqs(String json);
}
