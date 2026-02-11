package BlueAndPurple.invoicing.serviceNF;

import BlueAndPurple.invoicing.model.OrdersInvoicing;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceService {
    @Value("classpath:reports/invoice.jrxml")
    private Resource invoice;

    @Value("classpath:reports/shine.jpg")
    private Resource logo;

    public byte[] createInvoice(OrdersInvoicing ordersInvoicing){
        try (InputStream inputStream = invoice.getInputStream()){
            Map<String, Object> params = new HashMap<>();

            params.put("NAME", ordersInvoicing.dataOfClientsInvoicing().name());
            params.put("CPF", ordersInvoicing.dataOfClientsInvoicing().cpf());
            params.put("ADDRESS", ordersInvoicing.dataOfClientsInvoicing().address());
            params.put("EMAIL", ordersInvoicing.dataOfClientsInvoicing().email());
            params.put("CELLPHONENUMBER", ordersInvoicing.dataOfClientsInvoicing().cellphonenumber());

            params.put("DATA_ORDERS",ordersInvoicing.data());
            params.put("TOTAL_ORDERS", ordersInvoicing.total());

            params.put("LOGO", logo.getFile().getAbsolutePath());

            var dataSource = new JRBeanCollectionDataSource(ordersInvoicing.itemOfOrdersInvoicings());

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
