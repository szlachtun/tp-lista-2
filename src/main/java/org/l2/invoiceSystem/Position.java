package org.l2.invoiceSystem;

import java.util.Optional;
import java.util.Scanner;

class Position {
    private final String productName;
    private final double productNetPrice;
    private final int productCount;
    private final int productVatPer;
    private final double productGrossPrice;

    Position(String productName, double productNetPrice, int productCount, int productVatPer) {
        this.productName = productName;
        this.productNetPrice = productNetPrice;
        this.productCount = productCount;
        this.productVatPer = productVatPer;
        this.productGrossPrice = calculateGrossPrice(productNetPrice, productVatPer);
    }

    static Optional<Position> positionFactory() {
        var positionReader = new Scanner(System.in);

        System.out.println("Enter product name ('finish' - cancel creation): ");
        String tempProductName = positionReader.nextLine();

        if (tempProductName.equals("finish")) {
            return Optional.empty();
        }

        System.out.println("Enter product net price: ");
        double tempProductNetPrice = Double.parseDouble(positionReader.nextLine());

        System.out.println("Enter product count: ");
        int tempProductCount = Integer.parseInt(positionReader.nextLine());

        System.out.println("Enter product VAT in percents (without '%'): ");
        int tempProductVatPer = Integer.parseInt(positionReader.nextLine());

        double tempProductFinalNetPrice = tempProductNetPrice  * tempProductCount;

        var tempPosition = new Position(tempProductName, tempProductFinalNetPrice, tempProductCount, tempProductVatPer);
        do {
            System.out.print("Entered position is:\n" + tempPosition + "Add the position to invoice? y/n: ");
            String command = positionReader.nextLine();

            if (command.equalsIgnoreCase("y")) {
                return Optional.of(tempPosition);
            } else if (command.equalsIgnoreCase("n")) {
                return Optional.empty();
            }
        } while(true);

    }

    private double calculateGrossPrice(double productNetPrice, int productVatPer) {
        return productNetPrice / ((double) (100 - productVatPer) / 100);
    }

    @Override
    public String toString() {
        return String.format("|%s|%d|%f|%d|%f|\n", productName, productCount, productNetPrice, productVatPer,
                productGrossPrice);
    }
}
