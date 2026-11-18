package com.orderflow.factory;

import com.orderflow.model.ShippingType;
import com.orderflow.strategy.ShippingStrategy;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class ShippingStrategyFactory {

    private final Map<ShippingType, ShippingStrategy> strategies;

    public ShippingStrategyFactory(List<ShippingStrategy> strategies) {

        this.strategies = new EnumMap<>(ShippingType.class);

        for (ShippingStrategy strategy : strategies) {
            this.strategies.put(strategy.getType(), strategy);
        }
    }

    public ShippingStrategy getStrategy(ShippingType type) {

        ShippingStrategy strategy = strategies.get(type);

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Shipping type not supported: " + type
            );
        }

        return strategy;
    }
}

