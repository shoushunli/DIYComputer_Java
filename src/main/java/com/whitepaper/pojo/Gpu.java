package com.whitepaper.pojo;

public class Gpu {
    String sku;
    String vramBandwidth;
    String vramBitwidth;
    String vramSize;
    String vramType;
    Integer typeC;
    Integer dvi;
    Integer dp;
    Integer hdmi;
    Integer systemPower;
    Integer gpuPower;
    String freq;
    Integer streamProcessor;
    Double price;

    public Gpu() {
    }

    public Gpu(String sku, String vramBandwidth, String vramBitwidth, String vramSize, String vramType, Integer typeC, Integer dvi, Integer dp, Integer hdmi, Integer systemPower, Integer gpuPower, String freq, Integer streamProcessor, Double price) {
        this.sku = sku;
        this.vramBandwidth = vramBandwidth;
        this.vramBitwidth = vramBitwidth;
        this.vramSize = vramSize;
        this.vramType = vramType;
        this.typeC = typeC;
        this.dvi = dvi;
        this.dp = dp;
        this.hdmi = hdmi;
        this.systemPower = systemPower;
        this.gpuPower = gpuPower;
        this.freq = freq;
        this.streamProcessor = streamProcessor;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "sku='" + sku + '\'' +
                ", vramBandwidth='" + vramBandwidth + '\'' +
                ", vramBitwidth='" + vramBitwidth + '\'' +
                ", vramSize='" + vramSize + '\'' +
                ", vramType='" + vramType + '\'' +
                ", typeC=" + typeC +
                ", dvi=" + dvi +
                ", dp=" + dp +
                ", hdmi=" + hdmi +
                ", systemPower=" + systemPower +
                ", gpuPower=" + gpuPower +
                ", freq='" + freq + '\'' +
                ", streamProcessor=" + streamProcessor +
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

    public String getVramBandwidth() {
        return vramBandwidth;
    }

    public void setVramBandwidth(String vramBandwidth) {
        this.vramBandwidth = vramBandwidth;
    }

    public String getVramBitwidth() {
        return vramBitwidth;
    }

    public void setVramBitwidth(String vramBitwidth) {
        this.vramBitwidth = vramBitwidth;
    }

    public String getVramSize() {
        return vramSize;
    }

    public void setVramSize(String vramSize) {
        this.vramSize = vramSize;
    }

    public String getVramType() {
        return vramType;
    }

    public void setVramType(String vramType) {
        this.vramType = vramType;
    }

    public Integer getTypeC() {
        return typeC;
    }

    public void setTypeC(Integer typeC) {
        this.typeC = typeC;
    }

    public Integer getDvi() {
        return dvi;
    }

    public void setDvi(Integer dvi) {
        this.dvi = dvi;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
    }

    public Integer getHdmi() {
        return hdmi;
    }

    public void setHdmi(Integer hdmi) {
        this.hdmi = hdmi;
    }

    public Integer getSystemPower() {
        return systemPower;
    }

    public void setSystemPower(Integer systemPower) {
        this.systemPower = systemPower;
    }

    public Integer getGpuPower() {
        return gpuPower;
    }

    public void setGpuPower(Integer gpuPower) {
        this.gpuPower = gpuPower;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public Integer getStreamProcessor() {
        return streamProcessor;
    }

    public void setStreamProcessor(Integer streamProcessor) {
        this.streamProcessor = streamProcessor;
    }
}
