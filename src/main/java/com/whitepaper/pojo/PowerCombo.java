package com.whitepaper.pojo;

public class PowerCombo {
    Gpu gpu;
    Cpu cpu;
    Power power;

    public PowerCombo() {
    }

    public PowerCombo(Gpu gpu, Cpu cpu, Power power) {
        this.gpu = gpu;
        this.cpu = cpu;
        this.power = power;
    }

    @Override
    public String toString() {
        return "PowerCombo{" +
                "gpu=" + gpu +
                ", cpu=" + cpu +
                ", power=" + power +
                '}';
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }
}
