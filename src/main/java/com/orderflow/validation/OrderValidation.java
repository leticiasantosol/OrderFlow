package com.orderflow.validation;

import com.orderflow.model.PurchaseOrder;

public interface OrderValidation {

    void validate(PurchaseOrder order);
}
