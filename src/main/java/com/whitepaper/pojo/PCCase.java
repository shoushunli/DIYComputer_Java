package com.whitepaper.pojo;

public class PCCase {
    String sku;
    String formFactor;
    Double price;

    public PCCase() {
    }

    public PCCase(String sku, String formFactor, Double price) {
        this.sku = sku;
        this.formFactor = formFactor;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PCCase{" +
                "sku='" + sku + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", price=" + price +
                '}';
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
