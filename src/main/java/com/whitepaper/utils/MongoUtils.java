package com.whitepaper.utils;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.whitepaper.dao.impl.MongoBaseDao;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

public class MongoUtils{
    public <T> T toBean(BasicDBObject dbObject,Class<T> clzss){
        String realJson = dbObject.toJson(JsonWriterSettings.builder().build());
        T obj = JSON.parseObject(realJson,clzss);

        return obj;

    }

    public static <T> T toBean(Document document,Class<T> clzss){
        String realJson = document.toJson(JsonWriterSettings.builder().build());

        T obj = JSON.parseObject(realJson,clzss);

        return obj;

    }

    public static <T> BasicDBObject toDBObject(T object){
        String json = JSON.toJSONString(object);

        BasicDBObject basicDBObject = BasicDBObject.parse(json);

        return basicDBObject;

    }

    public static <T> Document toDocument(T object){
        String json = JSON.toJSONString(object);

        Document document = Document.parse(json);

        return document;

    }

    public static <T> List<Document> toDocumentList(List<T> list){
        List<Document> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Document document = toDocument(list.get(i));
            res.add(document);
        }
        return res;
    }

    public static void showAllData(String databaseName, String collectionName){

        MongoBaseDao mongoBaseDao = new MongoBaseDao();
        List<String> list = mongoBaseDao.selectAllDataInJson(databaseName, collectionName);
        System.out.println(databaseName + "数据库中的" + collectionName + "集合中的所有数据如下：");
        for (String each : list)
            System.out.println(each);
    }

    public static void showAllDatabaseName(){
        MongoClient conn = MongoDBConnection.getConn();
//        MongoClient mongoClient = mongoDBConnection.getConnByCredit();
        //查询所有数据库名称
        MongoIterable<String> dbNameList = conn.listDatabaseNames();
        System.out.println("所有数据库名称如下:");
        for(String dbName : dbNameList)
            System.out.println(dbName);
        conn.close();
    }

    public static void showAllSetName(String databaseName){
        MongoClient conn = MongoDBConnection.getConn();
        //创建数据库对象
        MongoDatabase database = conn.getDatabase(databaseName);
        //查询数据库中所有集合名称
        MongoIterable<String> colNameList = database.listCollectionNames();
        System.out.println(databaseName + "数据库中所有集合的名字如下：");
        for(String colName: colNameList)
            System.out.println(colName);
        conn.close();
    }

}