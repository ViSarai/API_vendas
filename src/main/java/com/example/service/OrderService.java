package com.example.service;

import com.example.domain.entity.Order;
import com.example.domain.enums.StatusOrder;
import com.example.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {
    Order save(OrderDTO dto);
    Optional<Order> getCompleteOrder(Integer id);
    void updateStatus(Integer id, StatusOrder status);

}
