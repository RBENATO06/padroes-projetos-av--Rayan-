public class Main {
    public static void main(String[] args) {
        Participant p1 = new Participant("Alice");
        Participant p2 = new Participant("Bruno");
        Participant p3 = new Participant("Carla");
        AuctioneerPanel panel = new AuctioneerPanel();
        BidAuditor auditor = new BidAuditor();

        AuctionLot artLot = new AuctionLot("Quadro Moderno", "Arte", 1000.00, 1200.00, new ArtCommissionStrategy());
        AuctionLot vehicleLot = new AuctionLot("Sedan Executivo", "Veiculos", 50000.00, 60000.00, new VehicleCommissionStrategy());

        artLot.addObserver(p1);
        artLot.addObserver(p2);
        artLot.addObserver(p3);
        artLot.addObserver(panel);
        artLot.addObserver(auditor);

        vehicleLot.addObserver(p1);
        vehicleLot.addObserver(p2);
        vehicleLot.addObserver(p3);
        vehicleLot.addObserver(panel);
        vehicleLot.addObserver(auditor);

        artLot.placeBid(p1, 1020.00);
        artLot.placeBid(p1, 1050.00);
        artLot.placeBid(p2, 1200.00);

        artLot.removeObserver(p3);
        System.out.println("Carla deixou de acompanhar o lote 'Quadro Moderno'.");

        artLot.placeBid(p1, 1300.00);

        artLot.setCommissionStrategy(new ElectronicsCommissionStrategy());
        System.out.println("Regra de comissao do lote 'Quadro Moderno' alterada para Eletronicos.");

        artLot.close();
        artLot.placeBid(p2, 1500.00);

        vehicleLot.placeBid(p3, 53000.00);
        vehicleLot.close();

        System.out.println();
        InvoiceComponent invoice = new BaseLotPurchase(artLot);
        invoice = new TransportInsuranceDecorator(invoice);
        invoice = new AuthenticityCertificateDecorator(invoice);
        invoice = new SpecialPackagingDecorator(invoice);
        invoice = new SpecialPackagingDecorator(invoice);

        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        SellerSettlement settlement = new SellerSettlement();
        settlement.print(artLot);
        settlement.print(vehicleLot);
    }
}
