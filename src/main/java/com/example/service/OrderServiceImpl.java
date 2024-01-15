package com.example.service;

import com.example.domain.entity.Client;
import com.example.domain.entity.Order;
import com.example.domain.entity.ItemOrder;
import com.example.domain.entity.Product;
import com.example.domain.enums.StatusOrder;
import com.example.domain.repo.ClientRepository;
import com.example.domain.repo.ItemOrderRepository;
import com.example.domain.repo.OrderRepository;
import com.example.domain.repo.ProductRepository;
import com.example.handler.exception.GenericHandlerException;
import com.example.handler.exception.OrderNotFoundExcpetion;
import com.example.rest.dto.ItemOrderDTO;
import com.example.rest.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepo;
    private final ProductRepository productRepo;
    private final ItemOrderRepository itemOrderRepo;

    @Override
    @Transactional
    public Order save(OrderDTO dto) {

        Integer idClient = dto.getClientId();
        Client client =  clientRepo.findById(idClient)
                .orElseThrow(() -> new GenericHandlerException("code of client invalid."));

        Order newOrder = new Order();
        newOrder.setTotal(dto.getTotal());
        newOrder.setDateOrder(LocalDate.now());
        newOrder.setClient(client);
        newOrder.setStatus(StatusOrder.REALIZED);

        List<ItemOrder> itemOrder =  transformItem(newOrder,dto.getItems());
        orderRepository.save(newOrder);
        itemOrderRepo.saveAll(itemOrder);
        newOrder.setItems(itemOrder);

        return newOrder;
    }

    @Override
    public Optional<Order> getCompleteOrder(Integer id) {
            return orderRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, StatusOrder status) {
        orderRepository.findById(id)
                .map( order ->{
                    order.setStatus(status);
                    return  orderRepository.save(order);
                }).orElseThrow(OrderNotFoundExcpetion::new);
    }

    private List<ItemOrder> transformItem(Order order, List<ItemOrderDTO> items){
        if (items.isEmpty()){
            throw  new GenericHandlerException("Add at least one item to proceed with your order.");
        }
        return items
                .stream()
                .map((dto ->{
                    Integer idProduct = dto.getProductId();
                    Product product = productRepo
                            .findById(idProduct)
                            .orElseThrow(
                                    () -> new GenericHandlerException("Invalid product code:" + idProduct)
                            );

                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setQuantity(dto.getQuantity());
                    itemOrder.setOrder(order);
                    itemOrder.setProduct(product);

                    return itemOrder;
                })).collect(Collectors.toList());
    }
}
