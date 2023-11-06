package org.l2.main;

import org.l2.invoicesystem.InvoiceIssuer;

/**
 * invoicesystem usage example.
  */
public class Main {
  /**
  * program entry point.
   */
  public static void main(String[] args) {
    var invoiceIssuer = new InvoiceIssuer();
    invoiceIssuer.run();
  }
}