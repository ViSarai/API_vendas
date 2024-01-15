package com.example.rest.controllers;

import com.example.domain.entity.Order;
import com.example.domain.entity.ItemOrder;
import com.example.domain.enums.StatusOrder;
import com.example.rest.dto.OrderDTO;
import com.example.rest.dto.ResponseBodyItemOrderDTO;
import com.example.rest.dto.ResponseBodyOrderDTO;
import com.example.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = service.save(orderDTO);
        return order.getId();
    }

    @GetMapping("{id}")
    public ResponseBodyOrderDTO getById(@PathVariable Integer id) {
        return service
                .getCompleteOrder(id)
                .map(this::convert)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Order not found."));
    }

    private ResponseBodyOrderDTO convert(Order order) {
        return ResponseBodyOrderDTO.builder()
                .code(order.getId())
                .dateOrder(order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .nameClient(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(convert(order.getItems()))
                .build();
    }

    private List<ResponseBodyItemOrderDTO> convert(List<ItemOrder> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(
                item -> ResponseBodyItemOrderDTO.builder()
                        .productDescription(item.getProduct().getDescription())
                        .unitPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusOrderDTO dto) {
        String newStatus = dto.getNewStatus();
        service.updateStatus(id, StatusOrder.valueOf(newStatus));
    }
}
