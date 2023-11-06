package org.l2.invoicesystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

record Invoice(int invoiceId, String companyName, LocalDate invoiceTerm,
               ArrayList<Position> positionList) {

  static Optional<Invoice> invoiceFactory(int invoiceId) {
    var invoiceReader = new Scanner(System.in);
    var tempPositionList = new ArrayList<Position>();

    System.out.print("Enter invoice recipient (\"cancel\" - abandon invoice): ");
    String tempCompanyName = invoiceReader.nextLine();
    if (tempCompanyName.equalsIgnoreCase("cancel")) {
      return Optional.empty();
    }

    System.out.print("Enter invoice term (format yyyy-mm-dd): ");
    LocalDate tempInvoiceTerm = LocalDate.parse(invoiceReader.nextLine());

    do {
      var position = Position.positionFactory();
      if (position.isEmpty()) {
        break;
      } else {
        tempPositionList.add(position.get());
      }
    } while (true);

    return Optional.of(new Invoice(invoiceId, tempCompanyName, tempInvoiceTerm, tempPositionList));
  }

  void printInvoiceDetails() {
    System.out.printf("""
        --------------------------------------------------------------------------------
        Invoice ID: %d
        Recipient company: %s;  Payment term: %s
                        
        Product name | Count | Net price | VAT | Gross price
        \n""", this.invoiceId, this.companyName, this.invoiceTerm);
    for (var position : positionList) {
      System.out.println(position);
    }
    System.out.println("--------------------------------------------------------------"
        + "------------------\n");
  }

  @Override
  public String toString() {
    return String.format("|%d|%s|%s|", invoiceId, companyName, invoiceTerm);
  }
}
