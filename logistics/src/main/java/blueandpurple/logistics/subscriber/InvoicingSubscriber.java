package blueandpurple.logistics.subscriber;

import blueandpurple.logistics.service.ShippingOrdersService;
import blueandpurple.logistics.subscriber.representation.UpdateInvoicingRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


@Component
@Slf4j

public class InvoicingSubscriber {
    private final ObjectMapper objectMapper;
    private final ShippingOrdersService shippingOrdersService;


    public InvoicingSubscriber(ObjectMapper objectMapper,
                               ShippingOrdersService shippingOrdersService) {
        this.objectMapper = objectMapper;
        this.shippingOrdersService = shippingOrdersService;
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
    topics = "${blueandpurple.config.kafka.topics.orders-invoicing}")
    public void listen(String json){
        log.info("Receiving order for shipment: {} ", json);

        try{
            var representation = objectMapper.readValue(json, UpdateInvoicingRepresentation.class);
            shippingOrdersService.send(representation.codigo(), representation.urlNotaFiscal());

            log.info("Order processed successfully. Codigo: {}", representation.codigo());
            

        } catch (Exception e) {
            log.error("Error preparing order for shipment", e);
        }

    }
}
