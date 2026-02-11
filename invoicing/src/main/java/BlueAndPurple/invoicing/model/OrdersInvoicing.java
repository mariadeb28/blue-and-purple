package BlueAndPurple.invoicing.model;

import java.math.BigDecimal;
import java.util.List;

public record OrdersInvoicing(
        Long codigo,
        DataOfClientsInvoicing dataOfClientsInvoicing,
        String data,
        BigDecimal total,
        List<ItemOfOrdersInvoicing> itemOfOrdersInvoicings
) {
}
