package BlueAndPurple.invoicing.subscriber.representation;


import java.math.BigDecimal;
import java.util.List;

public record DTOOrderRepresentation(
        Long codigo,
        Long codigoClient,
        String name,
        String cpf,
        String address,
        String email,
        String cellphonenumber,
        String dataOrders,
        BigDecimal total,
        List<DTOItemOrderRepresentation> itemOrdersDTOList
) {
}
