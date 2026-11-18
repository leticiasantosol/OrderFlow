package com.orderflow.dto;

import com.orderflow.model.ShippingType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(

        @NotEmpty
        List<@Valid OrderItemRequest> items,

        @NotNull
        ShippingType shippingType

) {
}
