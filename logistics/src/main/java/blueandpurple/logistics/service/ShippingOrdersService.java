package blueandpurple.logistics.service;

import blueandpurple.logistics.model.StatusOrders;
import blueandpurple.logistics.model.UpdateSendOrders;
import blueandpurple.logistics.publisher.ShippingOrdersPublisher;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShippingOrdersService {
    private final ShippingOrdersPublisher publisher;

    public ShippingOrdersService(ShippingOrdersPublisher publisher) {
        this.publisher = publisher;
    }

    public void send(Long codigoOrders, String urlNotaFiscal){
        var codigoRastreio = createCodigoRastreio();
        var UpdateRepresentation =
                new UpdateSendOrders(codigoOrders,
                        StatusOrders.SENT,
                        codigoRastreio);
        publisher.send(UpdateRepresentation);

    }

    private String createCodigoRastreio(){
        var random = new Random();

        char letra1 = (char) ('A' + random.nextInt(26));
        char letra2 = (char) ('A' + random.nextInt(26));

        int numeros = 100000000 + random.nextInt(900000000);

        return "" + letra1 + letra2 + numeros + "BR";

    }
}
