package org.l2.invoicesystem;

import java.util.ArrayList;

class InvoiceBase {
  private final ArrayList<Invoice> invoiceList;
  private int nextInvoiceId;

  InvoiceBase() {
    this.invoiceList = new ArrayList<>();
    this.nextInvoiceId = 0;
  }

  void getInvoicesList() {
    System.out.println("ID | Company | Invoice term");
    for (var invoice : this.invoiceList) {
      System.out.printf("%2d | %s | %s\n", invoice.getInvoiceId(), invoice.getCompanyName(),
          invoice.getInvoiceTerm().toString());
    }
  }

  void addInvoice() {
    var result = Invoice.invoiceFactory(this.nextInvoiceId);
    if (result.isEmpty()) {
      System.out.println("Invoice creation was cancelled");
    } else {
      invoiceList.add(this.nextInvoiceId, result.get());
      bumpNextInvoiceId();
    }
  }

  void getInvoiceDetails(int invoiceId) {
    this.invoiceList.get(invoiceId).printInvoiceDetails();
  }

  private void bumpNextInvoiceId() {
    this.nextInvoiceId += 1;
  }

}
