package BlueAndPurple.invoicing.subscriber.representation;

import java.math.BigDecimal;

public record DTOItemOrderRepresentation(
        Long codigoProducts,
        String nomeProduct,
        Integer quantity,
        BigDecimal valorUnitario,
        BigDecimal total
) {

}
