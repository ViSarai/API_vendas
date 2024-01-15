package com.example.domain.entity;

import com.example.domain.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "date_order")
    private LocalDate dateOrder;
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrder status;
    @OneToMany(mappedBy = "order")
    private List<ItemOrder> items;

}
