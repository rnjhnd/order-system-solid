public class OrderManager {
    private final Order order;
    private final InvoiceGenerator invoiceGenerator;
    private final EmailNotifier emailNotifier;

    public OrderManager(Order order, InvoiceGenerator invoiceGenerator, EmailNotifier emailNotifier) {
        this.order = order;
        this.invoiceGenerator = invoiceGenerator;
        this.emailNotifier = emailNotifier;
    }

    public void processOrder(double price, int quantity, String customerName, String address, String invoiceFile, String email) {
        order.calculateTotal(price, quantity);
        order.placeOrder(customerName, address);
        invoiceGenerator.generateInvoice(invoiceFile);
        emailNotifier.sendEmailNotification(email);
    }
}