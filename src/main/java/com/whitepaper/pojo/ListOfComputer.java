package com.whitepaper.pojo;

public class ListOfComputer {
    Cpu cpu;
    Gpu gpu;
    Memory memory;
    HDD hdd;
    Motherboard motherboard;
    PCCase pcCase;
    Power power;
    SSD ssd;
    Double price;
    Double score;

    @Override
    public String toString() {
        return "ListOfComputer{" +
                "cpu=" + cpu +
                ", gpu=" + gpu +
                ", memory=" + memory +
                ", hdd=" + hdd +
                ", motherboard=" + motherboard +
                ", pcCase=" + pcCase +
                ", power=" + power +
                ", ssd=" + ssd +
                ", price=" + price +
                ", score=" + score +
                '}';
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public PCCase getPcCase() {
        return pcCase;
    }

    public void setPcCase(PCCase pcCase) {
        this.pcCase = pcCase;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public SSD getSsd() {
        return ssd;
    }

    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }
}
