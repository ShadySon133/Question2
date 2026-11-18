
package com.mycompany.productapp;

/**
 *
 * @author Shady
 */

public class ProductCalculations implements IProductCalculations {
    private double price;
    // Constructor to accept the base price


 
public ProductCalculations(double price) {
    this.price = price;
}

    @Override
    public double GetVat() {
        // VAT: 15% of the product price
        return price * 0.15;
    }

    @Override
    public double GetCommission() {
        // Commission: 5% of the product price
        return price * 0.05;
    }

    @Override
    public double GetTotal() {
        // Total: Price + VAT + Commission
        return price + GetVat() + GetCommission();
    }
}
