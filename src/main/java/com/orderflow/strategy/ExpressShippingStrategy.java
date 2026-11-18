package com.orderflow.strategy;

import com.orderflow.model.ShippingType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExpressShippingStrategy implements ShippingStrategy {

    @Override
    public ShippingType getType() {
        return ShippingType.EXPRESS;
    }

    @Override
    public BigDecimal calculate(BigDecimal orderTotal) {
        return new BigDecimal("25.00");
    }
}

