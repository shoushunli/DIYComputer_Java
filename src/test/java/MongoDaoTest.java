import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.whitepaper.dao.impl.MongoBaseDao;
import com.whitepaper.pojo.Cpu;
import com.whitepaper.service.ComputerService;
import com.whitepaper.service.ComputerServiceImpl;
import com.whitepaper.utils.MongoDBConnection;
import com.whitepaper.utils.MongoUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import sun.plugin2.gluegen.runtime.CPU;

import java.util.List;

public class MongoDaoTest {

    MongoBaseDao mongoBaseDao = new MongoBaseDao();
    ComputerService computerService = new ComputerServiceImpl();

    @Test
    public void test01(){
        MongoBaseDao mongoBaseDao = new MongoBaseDao();
        List<String> list = mongoBaseDao.selectAllDataInJson("computer", "computer");
        print(list);

    }

    public <T> void print(List<T> list){
        for (T each : list)
            System.out.println(each);
    }

    @Test
    public void test(){
        Cpu cpu = new Cpu();
        cpu.setPrice(12.33);
        cpu.setSku("hahahahz");

        MongoUtils.showAllData("computer", "computer");
        mongoBaseDao.insertData("computer", "computer", cpu);
        MongoUtils.showAllData("computer", "computer");
        mongoBaseDao.deleteData("computer", "computer", MongoUtils.toDocument(cpu));
        MongoUtils.showAllData("computer", "computer");
    }

    @Test
    public void wenhao(){
        MongoUtils.showAllDatabaseName();
        MongoUtils.showAllSetName("computer");
        MongoUtils.showAllData("computer", "computer");
    }
}
