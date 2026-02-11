package BlueAndPurple.invoicing.publisher;

import BlueAndPurple.invoicing.model.OrdersInvoicing;
import BlueAndPurple.invoicing.publisher.representation.StatusOrders;
import BlueAndPurple.invoicing.publisher.representation.UpdateStatusOrders;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvoicingPublisher {
    private static final Logger log = LoggerFactory.getLogger(InvoicingPublisher.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${blueandpurple.config.kafka.topics.orders-invoicing}")
    private String topic;

    public InvoicingPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(OrdersInvoicing ordersInvoicing, String urlNotaFiscal){
        try{
            var representation = new UpdateStatusOrders(ordersInvoicing.codigo(),
                    StatusOrders.INVOICED,
                    urlNotaFiscal);

            String json  = objectMapper.writeValueAsString(representation);

            kafkaTemplate.send(topic, "data", json);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}
