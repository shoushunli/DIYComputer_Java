import com.google.gson.Gson;
import com.whitepaper.dao.HardwareDao;
import com.whitepaper.dao.impl.HardwareDaoImpl;
import com.whitepaper.dao.impl.MongoBaseDao;
import com.whitepaper.pojo.*;
import com.whitepaper.service.ComputerServiceImpl;
import com.whitepaper.utils.MongoUtils;
import org.junit.Test;

import java.util.List;

public class DaoTest {

    HardwareDao hardwareDao = new HardwareDaoImpl();

    ComputerServiceImpl computerServiceImpl = new ComputerServiceImpl();

    @Test
    public void queryCpu() {
        List<Cpu> list = hardwareDao.queryCpu("heavy");
        for (Cpu each : list){
            System.out.println(each);
        }
    }

    @Test
    public void queryGpu(){
        List<Cpu> cpus = hardwareDao.queryCpu("light");
        for (Cpu each : cpus){
            System.out.println(each);
        }
        List<Gpu> gpus = hardwareDao.queryGpu(cpus, "light");
        System.out.println("**********************");
        for (Cpu each : cpus){
            System.out.println(each);
        }
        System.out.println("**********************");
        if (gpus != null) {
            for (Gpu each : gpus) {
                System.out.println(each);
            }
        }
    }

    @Test
    public void queryMemory(){
        List<Memory> memories = hardwareDao.queryMemory("large");
        print(memories);

    }

    public <T> void print(List<T> list){
        for (T each : list)
            System.out.println(each);
    }

    @Test
    public void queryPower(){
        List<Power> powers = hardwareDao.queryPower(0);
        print(powers);
    }

    @Test
    public void queryPowerCombo(){
        List<Cpu> cpus = hardwareDao.queryCpu("light");
        List<Gpu> gpus = hardwareDao.queryGpu(cpus, "heavy");
        List<PowerCombo> powerCombos = hardwareDao.queryPowerCombo(cpus, gpus, 450);
        if (powerCombos != null){
            for (PowerCombo powerCombo : powerCombos){
                print(powerCombos);
            }
        }

    }

    @Test
    public void queryPccase(){
        List<PCCase> pcCases = hardwareDao.queryPccase("three");
        print(pcCases);
    }
    @Test
    public void queryMotherboard(){
        List<Motherboard> pcCases = hardwareDao.queryMotherBoard("three");
        print(pcCases);
    }

    @Test
    public void querySSD(){
        List<SSD> ssds = hardwareDao.querySSD("one");
        print(ssds);
    }

    @Test
    public void queryHDD(){
        List<HDD> hdds = hardwareDao.queryHDD("three");
        print(hdds);
    }

    @Test
    public void queryList(){
        String cpuType = "heavy";
        String gpuType = "extreme";
        String memoryType = "small";
        String powerType = "0";
        String pccAndMotherboardType = "three";
        String ssdType = "one";
        String hddType = "two";
        List<ListOfComputer> listOfComputers = computerServiceImpl.queryForListOfComputer(cpuType, gpuType, memoryType, powerType, pccAndMotherboardType, ssdType, hddType);
        System.out.println("按照要求组合的清单数是：" + listOfComputers.size());

        int number = 3;
        List<ListOfComputer> list = computerServiceImpl.recommandList(listOfComputers, 1200.0, number);
        if (list == null) return;
        for (int i = 0; i < number; i++){
            String s = "cpu: " + list.get(i).getCpu().getSku() + "   \t"
                    + "power: " + list.get(i).getPower().getSku() + "   \t"
                    + "pccase: " + list.get(i).getPcCase().getSku() + "   \t"
                    + "motherboard: " + list.get(i).getMotherboard().getSku() + "   \t"
                    + "memory: " + list.get(i).getMemory().getSku() + "   \t"
                    + "ssd: " + list.get(i).getSsd().getSku() + "   \t";
            if (list.get(i).getGpu() != null)
                s += "gpu: " + list.get(i).getGpu().getSku() + "   \t";
            if (list.get(i).getHdd() != null)
                s += "hdd: " + list.get(i).getHdd().getSku() + "   \t";
            s += "price: " + list.get(i).getPrice() + "   \t";
            s += "score:" + list.get(i).getScore();

            System.out.println(s);
        }

        MongoBaseDao mongoBaseDao = new MongoBaseDao();
        MongoUtils.showAllData("computer", "computer");
//        mongoBaseDao.deleteData("computer", "computer", "name", "哇哈哈");
//        mongoBaseDao.insertData("computer", "computer", list);
        MongoUtils.showAllData("computer", "computer");
        List<String> jsonList = mongoBaseDao.selectAllDataInJson("computer", "computer");
        Gson gson = new Gson();
        for (String each : jsonList){
            ListOfComputer listOfComputer = gson.fromJson(each, ListOfComputer.class);
            System.out.println(listOfComputer);
        }


    }


}
