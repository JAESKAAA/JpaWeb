package com.example.jpawebapp.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.jpawebapp.entity.Address;
import com.example.jpawebapp.entity.Book;
import com.example.jpawebapp.entity.Item;
import com.example.jpawebapp.entity.Member;
import com.example.jpawebapp.entity.Order;
import com.example.jpawebapp.entity.OrderStatus;
import com.example.jpawebapp.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    Member member;

    Item item;
    @BeforeEach
    public void setup() {
        member = new Member("회원1", new Address("서울","강가","123"));
        item = new Book();
        item.setName("JPA프로그래밍");
        item.setStockQuantity(10);
        item.setPrice(10000);

        em.persist(member);
        em.persist(item);

    }

    @Test
    @DisplayName("상품 주문이 처리됨.")
    public void orderTest() {
        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order orderResult = orderRepository.findOne(orderId);

        assertThat(orderResult.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(orderResult.getTotalPrice()).isEqualTo(10000);


    }
}