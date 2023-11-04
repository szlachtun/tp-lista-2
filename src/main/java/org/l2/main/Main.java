package org.l2.main;
import org.l2.invoiceSystem.InvoiceIssuer;

public class Main {
    public static void main(String[] args) {
        var invoiceIssuer = new InvoiceIssuer();
        invoiceIssuer.issueInvoice();
    }
}