package BlueAndPurple.invoicing.model;

import java.math.BigDecimal;


public class ItemOfOrdersInvoicing{
    private Long codigo;
    private String description;
    private BigDecimal valorUnitario;
    private Integer quantity;
    private BigDecimal total;

    public ItemOfOrdersInvoicing(Long codigo, String description, BigDecimal valorUnitario, Integer quantity, BigDecimal total) {
        this.codigo = codigo;
        this.description = description;
        this.valorUnitario = valorUnitario;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
