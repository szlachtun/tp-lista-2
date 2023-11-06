package org.l2.invoicesystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * org.l2.invoicesystem package API
 */
public class InvoiceIssuer implements Export2Db {
  private final InvoiceBase invoiceBase;

  public InvoiceIssuer() {
    this.invoiceBase = new InvoiceBase();
  }

  void issueInvoice() {
    this.invoiceBase.addInvoice();
  }

  void showInvoices() {
    this.invoiceBase.getInvoicesList();
  }

  void showInvoiceDetails(int invoiceId) {
    this.invoiceBase.getInvoiceDetails(invoiceId);
  }

  void exportInvoice(int invoiceId) {
    this.saveInvoice(invoiceBase.getInvoice(invoiceId));
  }

  public void run() {
    var scanner = new Scanner(System.in);
    final String menu = """
        add                 - create new invoice
        get list            - get short list of issued invoices
        get detail [index]  - get details about [index] id invoice
        export [index]      - export [index] id invoice
        help                - show this menu
        exit                - close program
        """;
    final String choice = "input: ";
    final String wrongInput = "Wrong input";

    while (true) {
      System.out.println(menu);
      System.out.print(choice);
      try {
        String result = scanner.nextLine();

        if (result.equals("add"))
          issueInvoice();

        else if (result.equals("get list"))
          showInvoices();

        else if (result.startsWith("get detail ")) {
          int index;
          try {
            var command = new ArrayList<>(List.of(result.split(" ")));
            index = Integer.parseInt(command.getLast());

            if (index >= invoiceBase.getInvoiceListSize() || index < 0) {
              System.out.println(wrongInput);
              continue;
            }
          } catch (IllegalArgumentException ex) {
            System.out.println(wrongInput);
            continue;
          }

          showInvoiceDetails(index);

        } else if (result.startsWith("export ")) {
          int index;
          try {
            var command = new ArrayList<>(List.of(result.split(" ")));
            index = Integer.parseInt(command.getLast());

            if (index >= invoiceBase.getInvoiceListSize() || index < 0) {
              System.out.println(wrongInput);
              continue;
            }
          } catch (IllegalArgumentException ex) {
            System.out.println(wrongInput);
            continue;
          }
          exportInvoice(index);
        } else if (result.equals("exit")) {
          break;
        }

      } catch (InputMismatchException ex) {
        System.out.println(wrongInput);
      }
    }

  }
}
