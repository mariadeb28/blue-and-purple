package blueandpurple.logistics.publisher;

import blueandpurple.logistics.model.UpdateSendOrders;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class ShippingOrdersPublisher {
    private static final Logger log = LoggerFactory.getLogger(ShippingOrdersPublisher.class);
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${blueandpurple.config.kafka.topics.orders-sent}")
    private String topico;

    public ShippingOrdersPublisher(ObjectMapper objectMapper,
                                   KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UpdateSendOrders updateSendOrders){
        log.info("Publishing submitted request {} ", updateSendOrders.codigo());

        try{
            var json = objectMapper.writeValueAsString(updateSendOrders);
            kafkaTemplate.send(topico, "data", json);
            log.info("The submitted request has been published {}, codigo de rastreio: {}",
                    updateSendOrders.codigo(),
                    updateSendOrders.codigoRastreio());

        } catch (Exception e) {
            log.error("Error publishing order submission.", e);
        }

    }

}
