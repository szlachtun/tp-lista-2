package org.l2.invoice;

record Position(String productName, double productNetPrice, int productCount, int productVatPer, double productGrossPrice) {

    @Override
    public String toString() {
        return String.format("|%s|%d|%f|%d|%f|", productName, productCount, productNetPrice, productVatPer,
                productGrossPrice);
    }
}
