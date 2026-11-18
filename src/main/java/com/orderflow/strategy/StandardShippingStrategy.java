package com.orderflow.strategy;

import com.orderflow.model.ShippingType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StandardShippingStrategy implements ShippingStrategy {

    @Override
    public ShippingType getType() {
        return ShippingType.STANDARD;
    }

    @Override
    public BigDecimal calculate(BigDecimal orderTotal) {

        BigDecimal minimumForFreeShipping =
                new BigDecimal("200.00");

        if (orderTotal.compareTo(minimumForFreeShipping) >= 0) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal("10.00");
    }
}

