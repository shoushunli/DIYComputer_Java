package com.whitepaper.service;

import com.whitepaper.dao.ComputerDao;
import com.whitepaper.dao.HardwareDao;
import com.whitepaper.dao.impl.ComputerDaoImpl;
import com.whitepaper.dao.impl.HardwareDaoImpl;
import com.whitepaper.pojo.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class ComputerServiceImpl implements ComputerService{

    HardwareDao hardwareDao = new HardwareDaoImpl();
    ComputerDao computerDao = new ComputerDaoImpl();

    public List<ListOfComputer> queryForListOfComputer(String cpuType, String gpuType, String memoryType, String powerType, String pccAndMotherboardType, String ssdType, String hddType){
        List<Cpu> cpus = hardwareDao.queryCpu(cpuType);
        List<Gpu> gpus = hardwareDao.queryGpu(cpus, gpuType);
        List<PowerCombo> powerCombos = hardwareDao.queryPowerCombo(cpus, gpus, Integer.parseInt(powerType));
        List<Memory> memories = hardwareDao.queryMemory(memoryType);
        List<PCCase> pcCases = hardwareDao.queryPccase(pccAndMotherboardType);
        List<Motherboard> motherboards = hardwareDao.queryMotherBoard(pccAndMotherboardType);
        List<SSD> ssds = hardwareDao.querySSD(ssdType);
        List<HDD> hdds = hardwareDao.queryHDD(hddType);

        List<ListOfComputer> res = new ArrayList<>();

        if (powerCombos != null && memories != null && pcCases != null && motherboards != null && ssds != null){

            for (PCCase pcCase : pcCases){
                for (Motherboard motherboard : motherboards){
                    if ("one".equals(pccAndMotherboardType)){
                        if ("MATX".equals(pcCase.getFormFactor()) && "ATX".equals(motherboard.getFormFactor()))
                            continue;
                        if ("ITX".equals(pcCase.getFormFactor()) && !"ITX".equals(motherboard.getFormFactor()))
                            continue;
                    }else if ("three".equals(pccAndMotherboardType)){
                        if ("MATX".equals(pcCase.getFormFactor()) && "ATX".equals(motherboard.getFormFactor()))
                            continue;
                    }
                    for (PowerCombo powerCombo : powerCombos){
                        if (!powerCombo.getCpu().getSocket().equals(motherboard.getSocket()))
                            continue;
                        for (Memory memory : memories){
                            for (SSD ssd : ssds){
                                if (hdds != null){
                                    for (HDD hdd : hdds){
                                        ListOfComputer listOfComputer = new ListOfComputer();
                                        listOfComputer.setCpu(powerCombo.getCpu());
                                        listOfComputer.setGpu(powerCombo.getGpu());
                                        listOfComputer.setPower(powerCombo.getPower());
                                        listOfComputer.setMemory(memory);
                                        listOfComputer.setMotherboard(motherboard);
                                        listOfComputer.setPcCase(pcCase);
                                        listOfComputer.setSsd(ssd);
                                        listOfComputer.setHdd(hdd);
                                        res.add(listOfComputer);
                                    }
                                }else {
                                    ListOfComputer listOfComputer = new ListOfComputer();
                                    listOfComputer.setCpu(powerCombo.getCpu());
                                    listOfComputer.setGpu(powerCombo.getGpu());
                                    listOfComputer.setPower(powerCombo.getPower());
                                    listOfComputer.setMemory(memory);
                                    listOfComputer.setMotherboard(motherboard);
                                    listOfComputer.setPcCase(pcCase);
                                    listOfComputer.setSsd(ssd);
                                    res.add(listOfComputer);
                                }
                            }
                        }
                    }
                }
            }

        }

        return res;
    }

    public List<ListOfComputer> recommandList(List<ListOfComputer> list, Double maximum, Integer number){
        if (list == null)
            return null;
        ListOfComputer[] res = new ListOfComputer[number];
        Map<ListOfComputer, Double> map = new HashMap<>();

        //创建一个用来计算GPU得分的map
        Map<String, Double> scoreOfGpu = new HashMap<>();
        scoreOfGpu.put("3090", 15.0);
        scoreOfGpu.put("6900 XT", 15.0);
        scoreOfGpu.put("3080", 14.0);
        scoreOfGpu.put("6800 XT", 14.0);
        scoreOfGpu.put("3070", 13.0);
        scoreOfGpu.put("2080 Ti", 13.0);
        scoreOfGpu.put("6800", 13.0);
        scoreOfGpu.put("2080 Super", 12.5);
        scoreOfGpu.put("2080", 12.0);
        scoreOfGpu.put("2070 Super", 11.0);
        scoreOfGpu.put("5700 XT", 10.0);
        scoreOfGpu.put("2070", 9.5);
        scoreOfGpu.put("2060 Super", 9.0);
        scoreOfGpu.put("5700", 8.0);
        scoreOfGpu.put("2060", 7.5);
        scoreOfGpu.put("5600 XT", 7.0);
        scoreOfGpu.put("1660 Ti", 6.5);
        scoreOfGpu.put("1660 Super", 6.0);
        scoreOfGpu.put("1660", 5.0);
        scoreOfGpu.put("5550 XT", 4.5);
        scoreOfGpu.put("1650 Super", 4.0);
        scoreOfGpu.put("1650", 3.0);
        scoreOfGpu.put("5300", 2.0);

        //记录一下满足价格的清单数
        int countOfList = 0;
        for (int i = list.size() - 1; i >= 0; i--){
            ListOfComputer curr = list.get(i);
            double price = curr.getCpu().getPrice() + curr.getMemory().getPrice()
                    + curr.getMotherboard().getPrice() + curr.getPcCase().getPrice() + curr.getPower().getPrice() + curr.getSsd().getPrice();
            if (curr.getHdd() != null)
                price += curr.getHdd().getPrice();
            if (curr.getGpu() != null)
                price += curr.getGpu().getPrice();
            if (price > maximum)
                continue;
            curr.setPrice(price);
            countOfList += 1;

            double score = 0;
            String skuOfCpu = curr.getCpu().getSku();
            //如果intel的cpu
            if (skuOfCpu.charAt(0) == 'i'){
                if ("KS".equals(skuOfCpu.substring(skuOfCpu.length() - 2, skuOfCpu.length())))
                    score += 2;
                else if ("KF".equals(skuOfCpu.substring(skuOfCpu.length() - 2, skuOfCpu.length())))
                    score +=1;
                else if ("K".equals(skuOfCpu.substring(skuOfCpu.length() - 1, skuOfCpu.length())))
                    score += 1;
                else if ("T".equals(skuOfCpu.substring(skuOfCpu.length() - 1, skuOfCpu.length())))
                    score -= 1;
                if ("10".equals(skuOfCpu.substring(3, 5)))
                    score += 1;
            }else {
                //如果是amd的cpu
                if ("XT".equals(skuOfCpu.substring(skuOfCpu.length() - 2, skuOfCpu.length())))
                    score += 2;
                else if ("X".equals(skuOfCpu.substring(skuOfCpu.length() - 1, skuOfCpu.length())))
                    score += 1;
                else if ("E".equals(skuOfCpu.substring(skuOfCpu.length() - 1, skuOfCpu.length())))
                    score -= 1;
                else if ("GE".equals(skuOfCpu.substring(skuOfCpu.length() - 2, skuOfCpu.length())))
                    score -= 1;
                if ("5".equals(skuOfCpu.substring(3, 4)))
                    score += 1;
            }
            //如果没有gpu
            if (curr.getGpu() == null){
                if (skuOfCpu.charAt(0) != 'i'){
                    score += 1;
                }
                //如果有gpu
            }else {

                String skuOfGpu = curr.getGpu().getSku();
                //计算判分字符target
                String left = "";
                String right = "";
                for (int j = 0; j < skuOfGpu.length(); j++){
                    if (skuOfGpu.charAt(j) >= '0' && skuOfGpu.charAt(j) <= '9'){
                        left = skuOfGpu.substring(j, j+4);
                        j += 5;
                        if (j < skuOfGpu.length()){
                            int start = j;
                            while (j < skuOfGpu.length() && skuOfGpu.charAt(j) != ' ')
                                j++;
                            right = skuOfGpu.substring(start, j);
                            if ("Founders".equals(right) || "Anniversary".equals(right) || "-".equals(right))
                                right = "";
                        }
                        break;
                    }
                }
                String target = "".equals(right) ? left : left + " " + right;
                score += scoreOfGpu.getOrDefault(target, 0.0);
            }
            String skuOfSsd = curr.getSsd().getSku();
            String stringOfSsd = skuOfSsd.substring(skuOfSsd.length() - 5, skuOfSsd.length());
            if (!"SATA3".equals(stringOfSsd))
                score += 1;

            curr.setScore(score);
            map.put(curr, score);
        }

        System.out.println("满足价格的清单数是：" + countOfList);

        if(map == null || map.size() == 0)
            return null;

        //用优先队列进行排序
        Comparator myComparator = new Comparator<ListOfComputer>() {
            @Override
            public int compare(ListOfComputer o1, ListOfComputer o2) {
                //分数低的在上面，先出去
                int res = sign(map.get(o1) - map.get(o2));
                if (res == 0){
                    //分数一样的时候，价格高的在上面，先出去
                    return sign(o2.getPrice() - o1.getPrice());
                }
                return res;
            }
        };

        Queue<ListOfComputer> heap = new PriorityQueue<>(myComparator);

        for (ListOfComputer each : map.keySet()){
            heap.add(each);
            if (heap.size() > number)
                heap.poll();
        }

        for (int i = number - 1; i >= 0 && heap.size() > 0; i--){
            res[i] = heap.poll();
            //保留两位小数
            double doublePrice = res[i].getPrice();
            doublePrice = new BigDecimal(doublePrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            res[i].setPrice(doublePrice);
        }

        return arrayToList(res);
    }



    public int sign(double n){
        if (n > 0)
            return 1;
        else if (n < 0)
            return -1;
        return 0;
    }

    public <T> List<T> arrayToList(T[] arr){
        List<T> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            if(arr[i] == null)
                continue;
            res.add(arr[i]);
        }
        return res;
    }

    @Override
    public void addComputer(ListOfComputer listOfComputer) {
        computerDao.addComputer(listOfComputer);
    }

    @Override
    public long deleteComputer(ListOfComputer computer) {
        return computerDao.deleteComputer(computer);
    }

    @Override
    public List<ListOfComputer> showData() {
        return computerDao.showData();
    }
}
