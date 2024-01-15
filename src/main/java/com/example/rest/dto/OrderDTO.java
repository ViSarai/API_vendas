package com.example.rest.dto;

import com.example.customervalidation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull(message = "{field.client.code.required}")
    private  Integer clientId;
    @NotNull(message = "{field.order.total.required}")
    private BigDecimal total;
    @NotEmptyList(message = "{field.order.items.required}")
    private List<ItemOrderDTO> items;
}
