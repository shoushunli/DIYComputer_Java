package com.whitepaper.pojo;

public class Motherboard {
    String sku;
    String chipset;
    String socket;
    String formFactor;
    Double price;

    public Motherboard() {
    }

    public Motherboard(String sku, String chipset, String socket, String formFactor, Double price) {
        this.sku = sku;
        this.chipset = chipset;
        this.socket = socket;
        this.formFactor = formFactor;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "sku='" + sku + '\'' +
                ", chipset='" + chipset + '\'' +
                ", socket='" + socket + '\'' +
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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
