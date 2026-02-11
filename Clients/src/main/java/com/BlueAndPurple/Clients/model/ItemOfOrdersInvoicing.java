package com.BlueAndPurple.Clients.model;

import java.math.BigDecimal;

public record ItemOfOrdersInvoicing(
        Long codigo,
        String description,
        BigDecimal valorUnitario,
        Integer quantity
) {
    public BigDecimal getTotal(){
        return BigDecimal.valueOf(this.quantity).multiply(this.valorUnitario);
    }
}
