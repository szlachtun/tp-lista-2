package org.l2.invoicesystem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/**
 * Data export logic.
 */
public interface Export2Db {

  /**
   * Hash target types.
   */
  enum HashType {
    COMPANY,
    PRODUCT
  }

  /**
   * Invoice export function.
   *
   * @param invoice target invoice to export
   */
  default void saveInvoice(Invoice invoice) {
    var companyTable = new ArrayList<>();
    var invoiceTable = new ArrayList<>();
    var positionHashList = new ArrayList<ArrayList<Object>>();

    try {
      companyTable.add(invoice.companyName());
      companyTable.add(getHash(invoice.companyName(), HashType.COMPANY));
    } catch (RuntimeException ex) {
      System.err.println("Error serializing Invoice");
      System.exit(-1);
    }

    try {
      invoiceTable.add(invoice.invoiceId());
      invoiceTable.add(companyTable.get(1));
      invoiceTable.add(invoice.invoiceTerm().toString());
    } catch (RuntimeException ex) {
      System.err.println("Error serializing Invoice");
      System.exit(-1);
    }

    for (var position : invoice.positionList()) {
      try {
        var positionHash = new ArrayList<>();
        positionHash.add(invoice.invoiceId());
        positionHash.add(position.getProductName());
        positionHash.add(getHash(position.getProductName(), HashType.PRODUCT));
        positionHash.add(position.getProductCount());
        positionHash.add(position.getProductNetPrice());
        positionHash.add(position.getProductVatPer());
        positionHash.add(position.getProductGrossPrice());

        positionHashList.add(positionHash);
      } catch (RuntimeException ex) {
        System.err.println("Error serializing Invoice");
        System.exit(-1);
      }
    }

    System.out.println("Company table: \n" + companyTable + "\n");
    System.out.println("Invoice table: \n" + invoiceTable + "\n");

    System.out.println("Position table:");
    for (var positionHash : positionHashList) {
      System.out.println(positionHash.toString());
    }

  }

  private String getHash(String input, HashType type) {
    String result = "";
    switch (type) {
      case COMPANY -> result += "cp";
      case PRODUCT -> result += "pr";
      default -> {
        System.err.println("Error: wrong hashing type");
        System.exit(-1);
      }
    }

    try {
      result += toHexString(getSha(input));
    } catch (NoSuchAlgorithmException ex) {
      System.err.println("Error: no hashing algorithm");
      System.exit(-1);
    }
    return result;
  }

  private static byte[] getSha(String input) throws NoSuchAlgorithmException {
    // Static getInstance method is called with hashing SHA
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    // digest() method called
    // to calculate message digest of an input
    // and return array of byte
    return md.digest(input.getBytes(StandardCharsets.UTF_8));
  }

  private static String toHexString(byte[] hash) {
    // Convert byte array into signed num representation
    BigInteger number = new BigInteger(1, hash);

    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));

    // Pad with leading zeros
    while (hexString.length() < 64) {
      hexString.insert(0, '0');
    }

    return hexString.toString();
  }
}
