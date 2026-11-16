public class SellerSettlement {
    public void print(AuctionLot lot) {
        if (!lot.isSold()) {
            System.out.printf("Acerto indisponivel: lote '%s' nao foi vendido.%n%n", lot.getName());
            return;
        }
        double salePrice = lot.getCurrentBid();
        double commission = lot.calculateCommission();
        double netAmount = salePrice - commission;
        System.out.println("=== ACERTO DO VENDEDOR ===");
        System.out.println("Lote: " + lot.getName());
        System.out.printf("Valor de venda: R$ %.2f%n", salePrice);
        System.out.printf("Comissao cobrada: R$ %.2f%n", commission);
        System.out.printf("Valor liquido a repassar: R$ %.2f%n%n", netAmount);
    }
}
