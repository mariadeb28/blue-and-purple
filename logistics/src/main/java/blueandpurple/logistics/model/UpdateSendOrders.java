package blueandpurple.logistics.model;

public record UpdateSendOrders (
        Long codigo,
        StatusOrders statusOrders,
        String codigoRastreio
) {
}
