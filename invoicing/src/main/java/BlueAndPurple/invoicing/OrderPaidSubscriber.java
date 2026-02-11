package BlueAndPurple.invoicing;

import BlueAndPurple.invoicing.mapper.InvoicingOrderMapper;
import BlueAndPurple.invoicing.model.OrdersInvoicing;
import BlueAndPurple.invoicing.serviceNF.GenerationInvoiceService;
import BlueAndPurple.invoicing.subscriber.representation.DTOOrderRepresentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderPaidSubscriber {
    private final ObjectMapper mapper;
    private final GenerationInvoiceService generationInvoiceService;
    private final InvoicingOrderMapper invoicingOrderMapper;
    private static final Logger log = LoggerFactory.getLogger(OrderPaidSubscriber.class);


    public OrderPaidSubscriber(ObjectMapper mapper,
                               GenerationInvoiceService generationInvoiceService,
                               InvoicingOrderMapper invoicingOrderMapper) {
        this.mapper = mapper;
        this.generationInvoiceService = generationInvoiceService;
        this.invoicingOrderMapper = invoicingOrderMapper;
    }

    @KafkaListener(groupId = "blueandpurple-invoicing",
            topics = "${blueandpurple.config.kafka.topics.orders-paid}")
    public void listen(String json){
        try{
            log.info("Receiving order for invoicing: {}", json);
            var representation = mapper.readValue(json, DTOOrderRepresentation.class);
            OrdersInvoicing map = invoicingOrderMapper.map(representation);
            generationInvoiceService.generation(map);

        } catch (Exception e){
            log.error("Error in the completion of paid orders: {}", e.getMessage());

        }

    }


}
