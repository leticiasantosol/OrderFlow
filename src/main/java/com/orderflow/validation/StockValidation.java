package com.orderflow.validation;

import com.orderflow.model.OrderItem;
import com.orderflow.model.PurchaseOrder;
import com.orderflow.model.Product;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Order(2)
@Component
public class StockValidation implements OrderValidation {

    @Override
    public void validate(PurchaseOrder order) {

        Map<Long, Integer> requestedQuantities = new HashMap<>();

        for (OrderItem item : order.getItems()) {

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException(
                        "Product quantity must be greater than zero."
                );
            }

            Product product = item.getProduct();

            if (product == null) {
                throw new IllegalArgumentException(
                        "Product cannot be null."
                );
            }

            requestedQuantities.merge(
                    product.getId(),
                    item.getQuantity(),
                    Integer::sum
            );
        }

        for (OrderItem item : order.getItems()) {

            Product product = item.getProduct();

            int requested = requestedQuantities.get(product.getId());

            if (product.getStock() < requested) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product: " + product.getName()
                );
            }
        }
    }
}
