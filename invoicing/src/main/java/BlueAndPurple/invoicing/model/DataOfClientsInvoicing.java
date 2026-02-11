package BlueAndPurple.invoicing.model;

public record DataOfClientsInvoicing(
        String name,
        String cpf,
        String address,
        String cellphonenumber,
        String email
) {
}
