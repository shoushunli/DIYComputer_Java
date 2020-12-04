package com.whitepaper.pojo;

public class HDD {
    String sku;
    Integer rpm;
    String capacity;
    Double price;
    String h_interface;

    public HDD() {
    }

    public HDD(String sku, Integer rpm, String capacity, Double price, String h_interface) {
        this.sku = sku;
        this.rpm = rpm;
        this.capacity = capacity;
        this.price = price;
        this.h_interface = h_interface;
    }

    @Override
    public String toString() {
        return "HDD{" +
                "sku='" + sku + '\'' +
                ", rpm=" + rpm +
                ", capacity='" + capacity + '\'' +
                ", price=" + price +
                ", h_interface='" + h_interface + '\'' +
                '}';
    }

    public String getH_interface() {
        return h_interface;
    }

    public void setH_interface(String h_interface) {
        this.h_interface = h_interface;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
