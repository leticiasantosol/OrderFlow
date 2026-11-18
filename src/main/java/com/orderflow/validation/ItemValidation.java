package com.orderflow.validation;

import com.orderflow.model.PurchaseOrder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class ItemValidation implements OrderValidation {

    @Override
    public void validate(PurchaseOrder order) {

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException(
                    "Order must contain at least one item."
            );
        }
    }
}
