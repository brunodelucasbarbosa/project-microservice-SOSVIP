package br.com.sosvip.apigerenciamentopedidos.dao;

import br.com.sosvip.apigerenciamentopedidos.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDAO extends JpaRepository<Order, Long> {

    Page<Order> findAllByClientId(Long clientId, Pageable pageable);

    Order findByClientId(Long clientId);

}
