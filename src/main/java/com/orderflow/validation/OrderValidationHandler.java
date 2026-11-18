package com.orderflow.validation;

import com.orderflow.model.PurchaseOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidationHandler {

    private final List<OrderValidation> validations;

    public OrderValidationHandler(List<OrderValidation> validations) {
        this.validations = validations;
    }

    public void validate(PurchaseOrder order) {

        for (OrderValidation validation : validations) {
            validation.validate(order);
        }
    }
}
