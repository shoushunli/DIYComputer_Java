package com.whitepaper.dao.impl;

import com.whitepaper.dao.HardwareDao;
import com.whitepaper.pojo.*;

import java.util.ArrayList;
import java.util.List;

public class HardwareDaoImpl extends BaseDao implements HardwareDao {
    @Override
    public List<Cpu> queryCpu(String cpuType) {
        String value1 = "";
        String value2 = "";
        switch (cpuType){
            case "light":
                value1 = "i3%";
                value2 = "R3%";
                break;
            case "medium":
                value1 = "i5%";
                value2 = "R5%";
                break;
            case "heavy":
                value1 = "i7%";
                value2 = "R7%";
                break;
            case "extreme":
                value1 = "i9%";
                value2 = "R9%";
                break;
        }

        String sql = "SELECT sku, coreGPU, socket, boostFreq, regularFreq, tdp, thread, core, price FROM CPU "
                + "NATURAL JOIN Hardware WHERE sku LIKE ? OR sku LIKE ?";

        return queryForList(Cpu.class, sql, value1, value2);
    }


    @Override
    public List<Gpu> queryGpu(List<Cpu> list, String gpuType) {
        switch (gpuType){
            case "mini":
                for(int i = list.size() - 1; i >= 0; i--){
                    Cpu cpu = list.get(i);
                    char start = cpu.getSku().charAt(0);
                    char end = cpu.getSku().charAt(cpu.getSku().length()-1);
                    if((start == 'i' && end == 'F') || (start == 'R' && end != 'G'))
                        list.remove(i);
                }
                return null;
            case "light":
                for(int i = list.size() - 1; i >= 0; i--){
                    Cpu cpu = list.get(i);
                    char start = cpu.getSku().charAt(0);
                    char end = cpu.getSku().charAt(cpu.getSku().length()-1);
                    if((start == 'i') || (start == 'R' && end != 'G'))
                        list.remove(i);
                }
                return null;
            case "medium":
                for(int i = list.size() - 1; i >= 0; i--){
                    Cpu cpu = list.get(i);
                    char start = cpu.getSku().charAt(0);
                    char end = cpu.getSku().charAt(cpu.getSku().length()-1);
                    if((start == 'i' && end != 'F') || (start == 'R' && end == 'G'))
                        list.remove(i);
                }
                String sql_m = "SELECT sku, vramBandwidth, vramBitwidth, vramSize, vramType, typeC, dvi, dp, hdmi, "
                + "systemPower, gpuPower, freq, streamProcessor, price FROM GPU "
                        + "NATURAL JOIN Hardware WHERE sku LIKE ? OR sku LIKE ? OR sku LIKE ? OR sku LIKE ?";
                String value1_m = "GTX 1650%";
                String value2_m = "GTX 1660%";
                String value3_m = "RX 5300%";
                String value4_m = "RX 5500 XT%";
                return queryForList(Gpu.class, sql_m, value1_m, value2_m, value3_m, value4_m);
            case "heavy":
                for(int i = list.size() - 1; i >= 0; i--){
                    Cpu cpu = list.get(i);
                    char start = cpu.getSku().charAt(0);
                    char end = cpu.getSku().charAt(cpu.getSku().length()-1);
                    if((start == 'i' && end != 'F') || (start == 'R' && end == 'G'))
                        list.remove(i);
                }
                String sql_h = "SELECT sku, vramBandwidth, vramBitwidth, vramSize, vramType, typeC, dvi, dp, hdmi, systemPower, gpuPower, freq, streamProcessor, price FROM GPU "
                        + "NATURAL JOIN Hardware WHERE sku LIKE ? OR sku LIKE ? OR sku LIKE ? OR sku LIKE ?";
                String value1_h = "RTX 2060%";
                String value2_h = "RTX 2070%";
                String value3_h = "RX 5600 XT%";
                String value4_h = "RX 5700%";
                return queryForList(Gpu.class, sql_h, value1_h, value2_h, value3_h, value4_h);
            case "extreme":
                for(int i = list.size() - 1; i >= 0; i--){
                    Cpu cpu = list.get(i);
                    char start = cpu.getSku().charAt(0);
                    char end = cpu.getSku().charAt(cpu.getSku().length()-1);
                    if((start == 'i' && end != 'F') || (start == 'R' && end == 'G'))
                        list.remove(i);
                }
                String sql_e = "SELECT sku, vramBandwidth, vramBitwidth, vramSize, vramType, typeC, dvi, dp, hdmi, systemPower, gpuPower, freq, streamProcessor, price FROM GPU "
                        + "NATURAL JOIN Hardware WHERE sku LIKE ? OR sku LIKE ? OR sku LIKE ? OR sku LIKE ? OR sku LIKE ? OR sku LIKE ?";
                String value1_e = "RTX 2080%";
                String value2_e = "RTX 3070%";
                String value3_e = "RTX 3080%";
                String value4_e = "RTX 3090%";
                String value5_e = "RX 6800%";
                String value6_e = "RX 6900%";
                return queryForList(Gpu.class, sql_e, value1_e, value2_e, value3_e, value4_e, value5_e, value6_e);
        }
        return null;
    }

    @Override
    public List<Memory> queryMemory(String memoryType) {
        String sql = "SELECT sku, freq, ddr, capacity, price FROM Memory "
                + "NATURAL JOIN Hardware WHERE sku LIKE ?";
        String value = "small".equals(memoryType) ? "%(8GBx2)" : "%(16GBx2)";
        return queryForList(Memory.class, sql, value);
    }

    @Override
    public List<Power> queryPower(int powerChoice) {
        if (powerChoice == 0) {
            String sql_1 = "SELECT sku, power, price FROM PowerSupply "
                    + "NATURAL JOIN Hardware";
            return queryForList(Power.class, sql_1);
        }else {
            String sql_2 = "SELECT sku, power, price FROM PowerSupply "
                    + "NATURAL JOIN Hardware where power = ?";
            return queryForList(Power.class, sql_2, powerChoice);
        }
    }

    @Override
    public List<PowerCombo> queryPowerCombo(List<Cpu> cpus, List<Gpu> gpus, int powerChoice) {
        List<PowerCombo> list = new ArrayList<>();
        if (cpus != null && gpus != null) {
            if (powerChoice == 0) {
                for (Cpu cpu : cpus) {
                    for (Gpu gpu : gpus) {
                        int miniPower = Math.min((cpu.getTdp() + gpu.getGpuPower() + 100), gpu.getSystemPower());
                        if (450 > miniPower)
                            powerChoice = 450;
                        else if (500 > miniPower)
                            powerChoice = 500;
                        else if (550 > miniPower)
                            powerChoice = 550;
                        else if (600 > miniPower)
                            powerChoice = 600;
                        else if (650 > miniPower)
                            powerChoice = 650;
                        else if (750 > miniPower)
                            powerChoice = 750;
                        if (powerChoice == 0)
                            continue;
                        List<Power> powers = queryPower(powerChoice);
                        for (Power power : powers) {
                            PowerCombo powerCombo = new PowerCombo();
                            powerCombo.setCpu(cpu);
                            powerCombo.setGpu(gpu);
                            powerCombo.setPower(power);
                            list.add(powerCombo);
                        }
                    }
                }
            } else {
                List<Power> powers = queryPower(powerChoice);
                if (powers != null) {
                    for (Cpu cpu : cpus) {
                        for (Gpu gpu : gpus) {
                            for (Power power : powers) {
                                PowerCombo powerCombo = new PowerCombo();
                                powerCombo.setCpu(cpu);
                                powerCombo.setGpu(gpu);
                                powerCombo.setPower(power);
                                list.add(powerCombo);
                            }
                        }
                    }
                }
            }
        }else if (cpus != null){
            if (powerChoice == 0) {
                for (Cpu cpu : cpus) {
                        int miniPower = cpu.getTdp() + 100;
                        if (450 > miniPower)
                            powerChoice = 450;
                        else if (500 > miniPower)
                            powerChoice = 500;
                        else if (550 > miniPower)
                            powerChoice = 550;
                        else if (600 > miniPower)
                            powerChoice = 600;
                        else if (650 > miniPower)
                            powerChoice = 650;
                        else if (750 > miniPower)
                            powerChoice = 750;
                        if (powerChoice == 0)
                            continue;
                        List<Power> powers = queryPower(powerChoice);
                        for (Power power : powers) {
                            PowerCombo powerCombo = new PowerCombo();
                            powerCombo.setCpu(cpu);
                            powerCombo.setPower(power);
                            list.add(powerCombo);
                        }
                }
            } else {
                List<Power> powers = queryPower(powerChoice);
                if (powers != null) {
                    for (Cpu cpu : cpus) {
                            for (Power power : powers) {
                                PowerCombo powerCombo = new PowerCombo();
                                powerCombo.setCpu(cpu);
                                powerCombo.setPower(power);
                                list.add(powerCombo);
                            }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<PCCase> queryPccase(String pccaseType) {
        switch (pccaseType){
            case "one":
                String sql1 = "SELECT sku, formFactor, price FROM PCCase NATURAL JOIN Hardware";
                return queryForList(PCCase.class, sql1);
            case "two":
                String sql2 = "SELECT sku, formFactor, price FROM PCCase NATURAL JOIN Hardware where formFactor = ?";
                return queryForList(PCCase.class, sql2, "ITX");
            case "three":
                String sql3 = "SELECT sku, formFactor, price FROM PCCase NATURAL JOIN Hardware where formFactor = ? "
                + "OR formFactor = ?";
                return queryForList(PCCase.class, sql3, "MATX", "ATX");
        }
        return null;
    }

    @Override
    public List<Motherboard> queryMotherBoard(String motherboardType) {
        switch (motherboardType){
            case "one":
                String sql1 = "SELECT sku, formFactor, socket, chipset, price FROM Motherboard NATURAL JOIN Hardware";
                return queryForList(Motherboard.class, sql1);
            case "two":
                String sql2 = "SELECT sku, formFactor, socket, chipset, price FROM Motherboard NATURAL JOIN Hardware "
                + "where formFactor = ?";
                return queryForList(Motherboard.class, sql2, "ITX");
            case "three":
                String sql3 = "SELECT sku, formFactor, socket, chipset, price FROM Motherboard NATURAL JOIN Hardware";
                return queryForList(Motherboard.class, sql3);
        }
        return null;

    }

    @Override
    public List<SSD> querySSD(String ssdType) {
        switch (ssdType){
            case "one":
                String sql1 = "select sku, memoryComponent, formFactor, capacity, price, s_interface "
                + "FROM SSD NATURAL JOIN Hardware where s_interface like '%SATA3'";
                return queryForList(SSD.class, sql1);
            case "two":
                String sql2 = "select sku, memoryComponent, formFactor, capacity, price, s_interface "
                        + "FROM SSD NATURAL JOIN Hardware where s_interface like '%NVMe'";
                return queryForList(SSD.class, sql2);
        }
        return null;
    }

    @Override
    public List<HDD> queryHDD(String hddType) {
        switch (hddType){
            case "one":
                return null;
            case "two":
                String sql1 = "select sku, rpm, capacity, price, h_interface "
                        + "FROM HDD NATURAL JOIN Hardware where capacity = '1TB'";
                return queryForList(HDD.class, sql1);
            case "three":
                String sql2 = "select sku, rpm, capacity, price, h_interface "
                        + "FROM HDD NATURAL JOIN Hardware where capacity = '2TB'";
                return queryForList(HDD.class, sql2);
        }
        return null;
    }


}
