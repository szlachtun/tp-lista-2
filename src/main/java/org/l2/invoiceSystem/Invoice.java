package org.l2.invoiceSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class Invoice {
    private final int invoiceID;
    private final String companyName;
    private final LocalDate invoiceTerm;
    private final ArrayList<Position> positionList;

    Invoice(int invoiceID, String companyName, LocalDate invoiceTerm, ArrayList<Position> positionList) {
        this.invoiceID = invoiceID;
        this.companyName = companyName;
        this.invoiceTerm = invoiceTerm;
        this.positionList = positionList;
    }

    static Optional<Invoice> invoiceFactory(int invoiceID) {
        var invoiceReader = new Scanner(System.in);
        var tempPositionList = new ArrayList<Position>();

        System.out.print("Enter invoice recipient (\"cancel\" - abandon invoice: ");
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

        return Optional.of(new Invoice(invoiceID, tempCompanyName, tempInvoiceTerm, tempPositionList));
    }

    void printInvoiceDetails() {

    }

    @Override
    public String toString() {
        return String.format("|%d|%s|%s|", invoiceID, companyName, invoiceTerm);
    }
}
