package org.model;

import java.math.BigDecimal;

public class PaymentMethod {
    public BigDecimal limit;
    private String name;
    private BigDecimal discount;

    public String getName() { return name; }
    public BigDecimal getDiscount() { return discount; }

    public void setName(String name) { this.name = name; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
}
