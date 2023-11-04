package org.l2.invoiceSystem;

import java.util.ArrayList;

class InvoiceBase {
    private final ArrayList<Invoice> invoiceList;
    private int nextInvoiceID;

    InvoiceBase() {
        this.invoiceList = new ArrayList<>();
        this.nextInvoiceID = 0;
    }
    void getInvoicesList() {
        System.out.println("ID | Company | Invoice term");
        for (var invoice : this.invoiceList) {
            System.out.printf("%2d | %s | %s\n", invoice.getInvoiceID(), invoice.getCompanyName(), invoice.getInvoiceTerm().toString());
        }
    }
    void addInvoice() {
        var result = Invoice.invoiceFactory(this.nextInvoiceID);
        if (result.isEmpty()) {
            System.out.println("Invoice creation was cancelled");
        } else {
            invoiceList.add(this.nextInvoiceID, result.get());
            bumpNextInvoiceID();
        }
    }
    void getInvoiceDetails(int invoiceID) {
        this.invoiceList.get(invoiceID).printInvoiceDetails();
    }
    private void bumpNextInvoiceID() {
        this.nextInvoiceID += 1;
    }

}
