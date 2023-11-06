package org.l2.invoicesystem;

/**
 * org.l2.invoicesystem package API
 */
public class InvoiceIssuer {
  private final InvoiceBase invoiceBase;

  public InvoiceIssuer() {
    this.invoiceBase = new InvoiceBase();
  }

  public void issueInvoice() {
    this.invoiceBase.addInvoice();
  }

  public void showInvoices() {
    this.invoiceBase.getInvoicesList();
  }

  public void showInvoiceDetails(int invoiceId) {
    this.invoiceBase.getInvoiceDetails(invoiceId);
  }
}
