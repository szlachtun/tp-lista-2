package org.l2.invoicesystem;

import java.util.Optional;
import java.util.Scanner;
import lombok.Getter;

@Getter
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

    System.out.print("Enter product name ('finish' - cancel creation): ");
    String tempProductName = positionReader.nextLine();

    if (tempProductName.equals("finish")) {
      return Optional.empty();
    }

    double tempProductNetPrice;
    while (true) {
      System.out.print("Enter product net price: ");
      try {
        tempProductNetPrice = Double.parseDouble(positionReader.nextLine());
        break;
      } catch (IllegalArgumentException ex) {
        System.out.println("Wrong value");
      }
    }

    int tempProductCount;
    while (true) {
      System.out.print("Enter product count: ");
      try {
        tempProductCount = Integer.parseInt(positionReader.nextLine());
        break;
      } catch (IllegalArgumentException ex) {
        System.out.println("Wrong value");
      }
    }

    int tempProductVatPer;
    while (true) {
      System.out.print("Enter product VAT in percents (without '%'): ");
      try {
        tempProductVatPer = Integer.parseInt(positionReader.nextLine());
        break;
      } catch (IllegalArgumentException ex) {
        System.out.println("Wrong value");
      }
    }

    double tempProductFinalNetPrice = Math.round(tempProductNetPrice * tempProductCount * 100.0) / 100.0;

    var tempPosition = new Position(tempProductName, tempProductFinalNetPrice,
        tempProductCount, tempProductVatPer);
    do {
      System.out.print("Entered position is:\n"+ "|Name|Count|Net Price|VAT, %|Gross Price\n" + tempPosition
          + "Add the position to invoice? y/n: ");
      String command = positionReader.nextLine();

      if (command.equalsIgnoreCase("y")) {
        return Optional.of(tempPosition);
      } else if (command.equalsIgnoreCase("n")) {
        return Optional.empty();
      }
    } while (true);

  }

  private double calculateGrossPrice(double productNetPrice, int productVatPer) {
    return productNetPrice / ((double) (100 - productVatPer) / 100);
  }

  @Override
  public String toString() {
    return String.format("|%s|%d|%f|%d|%f|\n", productName, productCount,
        productNetPrice, productVatPer, productGrossPrice);
  }
}
