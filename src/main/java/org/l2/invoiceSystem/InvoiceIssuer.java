package org.l2.invoiceSystem;

public class InvoiceIssuer {
    private InvoiceBase invoiceBase;
    public InvoiceIssuer() {
        this.invoiceBase = new InvoiceBase();
    }
    public void issueInvoice() {
        this.invoiceBase.addInvoice();
    }
    public void showInvoices() {
        this.invoiceBase.getInvoicesList();
    }
    public void showInvoiceDetails(int invoiceID) {
        this.invoiceBase.getInvoiceDetails(invoiceID);
    }
}
