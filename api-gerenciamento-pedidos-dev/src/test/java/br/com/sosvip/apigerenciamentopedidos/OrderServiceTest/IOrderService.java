package br.com.sosvip.apigerenciamentopedidos.OrderServiceTest;

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

import br.com.sosvip.apigerenciamentopedidos.dao.OrderDAO;
import br.com.sosvip.apigerenciamentopedidos.model.Order;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IOrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Test
    public void getAllOrdersTest() {

        Date date = new Date();

        Order order1 = new Order(
                1L,
                25.05,
                "Descricao",
                new Timestamp(date.getTime()),
                false);

        orderDAO.save(order1);

        Order order2 = new Order(
                1L,
                26.05,
                "Descricao",
                new Timestamp(date.getTime()),
                false);

        orderDAO.save(order2);

        List<Order> orders = new ArrayList<Order>();
        for (Order order : orderDAO.findAll()) {
            orders.add(order);
        }

        Assertions.assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    public void getOrderByIdTest() {

        Date date = new Date();

        Order order = new Order(
                1L,
                25.05,
                "Descricao",
                new Timestamp(date.getTime()),
                false);

        orderDAO.save(order);
        var order2 = orderDAO.findById(order.getId());

        Assertions.assertThat(orderDAO.findById(order.getId())).isNotNull();
        Assertions.assertThat(order2.get().getId()).isEqualTo(order.getId());
    }

    @Test
    public void getOrderByClientIdTest() {

        Date date = new Date();

        Order order = new Order(
                1L,
                25.05,
                "Descricao",
                new Timestamp(date.getTime()),
                false);

        orderDAO.save(order);
        var order2 = orderDAO.findByClientId(order.getClientId());

        Assertions.assertThat(order2.getClientId()).isEqualTo(order.getClientId());
    }

    @Test
    public void createOrderTest() {

        Date date = new Date();

        Order order = new Order(
                1L,
                25.05,
                "Descricao",
                new Timestamp(date.getTime()),
                false);

        orderDAO.save(order);

        Assertions.assertThat(order.getClientId()).isEqualTo(1L);
        Assertions.assertThat(order.getTotalAmount()).isEqualTo(25.05);
        Assertions.assertThat(order.getDescription()).isEqualTo("Descricao");
        Assertions.assertThat(order.getDate()).isEqualTo(new Timestamp(date.getTime()));
    }
}
