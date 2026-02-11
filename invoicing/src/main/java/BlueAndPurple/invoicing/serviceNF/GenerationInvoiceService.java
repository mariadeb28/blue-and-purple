package BlueAndPurple.invoicing.serviceNF;

import BlueAndPurple.invoicing.bucket.BucketFile;
import BlueAndPurple.invoicing.bucket.BucketService;
import BlueAndPurple.invoicing.model.OrdersInvoicing;
import BlueAndPurple.invoicing.publisher.InvoicingPublisher;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
@Slf4j
public class GenerationInvoiceService {
    private static final Logger log = LoggerFactory.getLogger(GenerationInvoiceService.class);
    private final InvoiceService invoiceService;
    private final BucketService bucketService;
    private final InvoicingPublisher invoicingPublisher;

    public GenerationInvoiceService(InvoiceService invoiceService, BucketService bucketService, InvoicingPublisher invoicingPublisher) {
        this.invoiceService = invoiceService;
        this.bucketService = bucketService;
        this.invoicingPublisher = invoicingPublisher;
    }

    public void generation(OrdersInvoicing ordersInvoicing){
        log.info("Generating an invoice for the order {} ", ordersInvoicing.codigo());

        try{
            byte[] byteArray = invoiceService.createInvoice(ordersInvoicing);

            String nameFile = String.format("invoice_order_%d.pdf", ordersInvoicing.codigo());

            var file = new BucketFile(nameFile,
                    new ByteArrayInputStream(byteArray),
                    MediaType.APPLICATION_PDF,
                    byteArray.length);

            bucketService.upload(file);

            String url = bucketService.getUrl(nameFile);
            invoicingPublisher.publish(ordersInvoicing, url);

            log.info("Generated invoice, file name: {}", nameFile);
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }

    }

}
