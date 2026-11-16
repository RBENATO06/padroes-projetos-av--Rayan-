public class InvoicePrinter {
    public void print(InvoiceComponent invoice) {
        System.out.println("=== FATURA DO COMPRADOR ===");
        System.out.println("Lote: " + invoice.getLotName());
        System.out.printf("Valor de venda: R$ %.2f%n", invoice.getSalePrice());
        for (String service : invoice.getServiceDescriptions()) {
            System.out.println(" - " + service);
        }
        System.out.printf("Total a pagar: R$ %.2f%n%n", invoice.getTotalCost());
    }
}
