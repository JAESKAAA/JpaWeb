package com.example.jpawebapp.service;

import com.example.jpawebapp.entity.Delivery;
import com.example.jpawebapp.entity.Item;
import com.example.jpawebapp.entity.Member;
import com.example.jpawebapp.entity.Order;
import com.example.jpawebapp.entity.OrderItem;
import com.example.jpawebapp.entity.OrderSearch;
import com.example.jpawebapp.repository.MemberRepository;
import com.example.jpawebapp.repository.OrderRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    @Autowired
    public OrderService(MemberRepository memberRepository, OrderRepository orderRepository, ItemService itemService) {
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    //주문
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 먼저 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);

        //배송정보
        Delivery delivery = new Delivery(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문취소
        order.cancel();
    }

    //주문 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
