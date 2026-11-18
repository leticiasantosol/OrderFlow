package com.orderflow.service;

import com.orderflow.dto.CreateOrderRequest;
import com.orderflow.dto.OrderItemRequest;
import com.orderflow.model.*;
import com.orderflow.repository.OrderRepository;
import com.orderflow.repository.ProductRepository;
import com.orderflow.validation.OrderValidationHandler;
import com.orderflow.factory.ShippingStrategyFactory;
import com.orderflow.strategy.ShippingStrategy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderValidationHandler validationHandler;
    private final ShippingStrategyFactory shippingFactory;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            OrderValidationHandler validationHandler,
            ShippingStrategyFactory shippingFactory) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.validationHandler = validationHandler;
        this.shippingFactory = shippingFactory;
    }

    @Transactional
    public PurchaseOrder createOrder(CreateOrderRequest request) {

        PurchaseOrder order = new PurchaseOrder();

        for (OrderItemRequest itemRequest : request.items()) {

            Product product = productRepository
                    .findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found: " + itemRequest.productId()
                    ));

            OrderItem item = new OrderItem(
                    product,
                    itemRequest.quantity()
            );

            order.addItem(item);
        }

        validationHandler.validate(order);

        ShippingStrategy strategy =
                shippingFactory.getStrategy(request.shippingType());

        order.setShippingType(request.shippingType());

        order.setShippingCost(
                strategy.calculate(order.calculateTotal())
        );

        for (OrderItem item : order.getItems()) {

            Product product = item.getProduct();

            product.setStock(
                    product.getStock() - item.getQuantity()
            );

            productRepository.save(product);
        }

        order.setStatus(OrderStatus.APPROVED);

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PurchaseOrder getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with id: " + id
                ));
    }
}
