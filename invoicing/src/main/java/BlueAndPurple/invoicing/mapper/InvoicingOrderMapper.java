package BlueAndPurple.invoicing.mapper;

import BlueAndPurple.invoicing.model.DataOfClientsInvoicing;
import BlueAndPurple.invoicing.model.ItemOfOrdersInvoicing;
import BlueAndPurple.invoicing.model.OrdersInvoicing;
import BlueAndPurple.invoicing.subscriber.representation.DTOItemOrderRepresentation;
import BlueAndPurple.invoicing.subscriber.representation.DTOOrderRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicingOrderMapper {
    public OrdersInvoicing map(DTOOrderRepresentation representation){
        DataOfClientsInvoicing dataOfClientsInvoicing = new DataOfClientsInvoicing(
                representation.name(),
                representation.cpf(),
                representation.address(),
                representation.cellphonenumber(),
                representation.email()
        );

        List<ItemOfOrdersInvoicing> itemOfOrdersInvoicings = representation.itemOrdersDTOList()
                .stream().map(this::mapItem).toList();

        return new OrdersInvoicing(representation.codigo(),
                dataOfClientsInvoicing,
                representation.dataOrders(),
                representation.total(),
                itemOfOrdersInvoicings);
    }

    private ItemOfOrdersInvoicing mapItem(DTOItemOrderRepresentation representation){
        return new ItemOfOrdersInvoicing(representation.codigoProducts(),
                representation.nomeProduct(),
                representation.valorUnitario(),
                representation.quantity(),
                representation.total());
    }

}
