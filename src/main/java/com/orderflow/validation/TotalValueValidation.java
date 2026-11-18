package com.orderflow.validation;

import com.orderflow.model.PurchaseOrder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Order(3)
@Component
public class TotalValueValidation implements OrderValidation {

    @Override
    public void validate(PurchaseOrder order) {

        BigDecimal total = order.calculateTotal();

        if (total == null || total.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Order total must be greater than zero."
            );
        }
    }
}
