package com.whitepaper.pojo;

public class Power {
    String sku;
    Integer power;
    Double price;

    public Power() {
    }

    @Override
    public String toString() {
        return "Power{" +
                "sku='" + sku + '\'' +
                ", power=" + power +
                ", price=" + price +
                '}';
    }

    public Power(String sku, Integer power, Double price) {
        this.sku = sku;
        this.power = power;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
