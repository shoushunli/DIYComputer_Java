package com.whitepaper.pojo;

public class Memory {
    String sku;
    String freq;
    String ddr;
    String capacity;
    Double price;

    public Memory() {
    }

    public Memory(String sku, String freq, String ddr, String capacity, Double price) {
        this.sku = sku;
        this.freq = freq;
        this.ddr = ddr;
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "sku='" + sku + '\'' +
                ", freq='" + freq + '\'' +
                ", ddr='" + ddr + '\'' +
                ", capacity='" + capacity + '\'' +
                ", price=" + price +
                '}';
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

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getDdr() {
        return ddr;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
