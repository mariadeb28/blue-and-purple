package BlueAndPurple.invoicing.publisher.representation;

public record UpdateStatusOrders(
        Long codigo,
        StatusOrders statusOrders,
        String urlNotaFiscal

) {
}
