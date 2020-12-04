package com.whitepaper.dao;

import com.whitepaper.pojo.*;

import java.util.List;

public interface HardwareDao {
    public List<Cpu> queryCpu(String cpuType);

    public List<Gpu> queryGpu(List<Cpu> list, String gpuType);

    public List<Memory> queryMemory(String memoryType);

    public List<Power> queryPower(int powerChoice);

    /**
     * 先调用queryCpu，在调用queryGpu，再调用queryPower，结果传入queryPowerCombo
     * @param cpus
     * @param gpus
     * @param powerChoice 等于0为系统计算，等于其它值为用户自定义
     * @return
     */
    public List<PowerCombo> queryPowerCombo(List<Cpu> cpus, List<Gpu> gpus, int powerChoice);

    public List<PCCase> queryPccase(String pccaseType);

    public List<Motherboard> queryMotherBoard(String motherboardType);

    public List<SSD> querySSD(String ssdType);

    public List<HDD> queryHDD(String hddType);



}
