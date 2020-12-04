package com.whitepaper.pojo;

public class Cpu {
    String sku;
    String coreGPU;
    String socket;
    String boostFreq;
    String regularFreq;
    Integer tdp;
    Integer thread;
    Integer core;
    Double price;

    public Cpu() {
    }

    public Cpu(String sku, String coreGPU, String socket, String boostFreq, String regularFreq, Integer tdp, Integer thread, Integer core, Double price) {
        this.sku = sku;
        this.coreGPU = coreGPU;
        this.socket = socket;
        this.boostFreq = boostFreq;
        this.regularFreq = regularFreq;
        this.tdp = tdp;
        this.thread = thread;
        this.core = core;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "sku='" + sku + '\'' +
                ", coreGPU='" + coreGPU + '\'' +
                ", socket='" + socket + '\'' +
                ", boostFreq='" + boostFreq + '\'' +
                ", regularFreq='" + regularFreq + '\'' +
                ", tdp=" + tdp +
                ", thread=" + thread +
                ", core=" + core +
                ", price=" + price +
                '}';
    }

    public String getCoreGPU() {
        return coreGPU;
    }

    public void setCoreGPU(String coreGPU) {
        this.coreGPU = coreGPU;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getBoostFreq() {
        return boostFreq;
    }

    public void setBoostFreq(String boostFreq) {
        this.boostFreq = boostFreq;
    }

    public String getRegularFreq() {
        return regularFreq;
    }

    public void setRegularFreq(String regularFreq) {
        this.regularFreq = regularFreq;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public Integer getThread() {
        return thread;
    }

    public void setThread(Integer thread) {
        this.thread = thread;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
