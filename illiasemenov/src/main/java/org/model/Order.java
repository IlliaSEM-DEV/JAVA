package org.model;

import java.math.BigDecimal;

public class Order {
    private String id;
    private String paymentMethod;
    private BigDecimal amount;

    // Getters
    public String getId() { return id; }
    public String getPaymentMethod() { return paymentMethod; }
    public BigDecimal getAmount() { return amount; }

    // Setters (если нужны для десериализации)
    public void setId(String id) { this.id = id; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
