package com.whitepaper.pojo;

public class SSD {
    String sku;
    String memoryComponent;
    String formFactor;
    String capacity;
    Double price;
    String s_interface;

    public SSD() {
    }

    public SSD(String sku, String memoryComponent, String formFactor, String capacity, Double price, String s_interface) {
        this.sku = sku;
        this.memoryComponent = memoryComponent;
        this.formFactor = formFactor;
        this.capacity = capacity;
        this.price = price;
        this.s_interface = s_interface;
    }

    @Override
    public String toString() {
        return "SSD{" +
                "sku='" + sku + '\'' +
                ", memoryComponent='" + memoryComponent + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", capacity='" + capacity + '\'' +
                ", price=" + price +
                ", s_interface='" + s_interface + '\'' +
                '}';
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMemoryComponent() {
        return memoryComponent;
    }

    public void setMemoryComponent(String memoryComponent) {
        this.memoryComponent = memoryComponent;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
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

    public String getS_interface() {
        return s_interface;
    }

    public void setS_interface(String s_interface) {
        this.s_interface = s_interface;
    }
}
