package com.whitepaper.dao.impl;

import com.google.gson.Gson;
import com.whitepaper.dao.ComputerDao;
import com.whitepaper.pojo.ListOfComputer;
import com.whitepaper.utils.MongoUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ComputerDaoImpl implements ComputerDao {

    MongoBaseDao mongoBaseDao = new MongoBaseDao();

    @Override
    public void addComputer(ListOfComputer listOfComputer) {
        mongoBaseDao.insertData("computer", "computer", listOfComputer);
    }

    @Override
    public long deleteComputer(ListOfComputer computer) {
        Document document = MongoUtils.toDocument(computer);
        return mongoBaseDao.deleteData("computer", "computer", document);
    }

    @Override
    public List<ListOfComputer> showData() {
        List<String> jsonList = mongoBaseDao.selectAllDataInJson("computer", "computer");
        Gson gson = new Gson();
        List<ListOfComputer> list = new ArrayList<>();
        for (String each : jsonList){
            ListOfComputer listOfComputer = gson.fromJson(each, ListOfComputer.class);
            list.add(listOfComputer);
        }
        return list;
    }
}
