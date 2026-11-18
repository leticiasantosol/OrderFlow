package com.orderflow.strategy;

import com.orderflow.model.ShippingType;
import java.math.BigDecimal;

public interface ShippingStrategy {

    ShippingType getType();

    BigDecimal calculate(BigDecimal orderTotal);

}
