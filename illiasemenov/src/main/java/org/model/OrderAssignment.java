package org.model;

import java.math.BigDecimal;

public class OrderAssignment {
    public String orderId;
    public String paymentMethodId;
    public BigDecimal loyaltyUsed;
    public BigDecimal cardUsed;

    public OrderAssignment(String orderId, String paymentMethodId, BigDecimal loyaltyUsed, BigDecimal cardUsed) {
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.loyaltyUsed = loyaltyUsed;
        this.cardUsed = cardUsed;
    }

    @Override
    public String toString() {
        return "Order " + orderId + ": " +
                "Used " + loyaltyUsed + " from points, " +
                cardUsed + " from " + paymentMethodId;
    }
}