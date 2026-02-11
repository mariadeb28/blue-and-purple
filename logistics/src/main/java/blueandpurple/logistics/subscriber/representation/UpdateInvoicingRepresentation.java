package blueandpurple.logistics.subscriber.representation;

import blueandpurple.logistics.model.StatusOrders;

public record UpdateInvoicingRepresentation(
        Long codigo,
        StatusOrders statusOrders,
        String urlNotaFiscal
) {
}
