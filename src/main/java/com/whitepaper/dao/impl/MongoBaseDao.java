package com.whitepaper.dao.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.whitepaper.utils.MongoDBConnection;
import com.whitepaper.utils.MongoUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoBaseDao {

    //查询集合中的所有数据
    public List<String> selectAllDataInJson(String databaseName, String collectionName){
        List<String> jsonList = new ArrayList<>();
        MongoClient conn = null;
        try{
            conn = MongoDBConnection.getConn();
            MongoDatabase database = conn.getDatabase(databaseName);
            //获取数据库中的user集合
            MongoCollection<Document> collection = database.getCollection(collectionName);
            //获取user集合中的文档
            FindIterable<Document> iterable = collection.find();
            //通过迭代器遍历找到的文档中的信息
            MongoCursor<Document> iterator = iterable.iterator();
            while(iterator.hasNext()){
                jsonList.add(iterator.next().toJson());
            }
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Select data field！");
        }finally {
            MongoDBConnection.close(conn);
        }
        return jsonList;
    }


    //插入多条数据
    public <T> void insertData(String databaseName, String collectionName, List<T> data){
        List<String> jsonList = new ArrayList<>();
        MongoClient conn = null;
        try{
            conn  = MongoDBConnection.getConn();
            MongoDatabase database = conn.getDatabase(databaseName);
            List<Document> documents = MongoUtils.toDocumentList(data);
            MongoCollection mongoCollection = database.getCollection(collectionName);
//            //一次插入一条数据
//            Document document = new Document("user_id","4")
//                    .append("user_name","test")
//                    .append("user_pwd","test");
//            mongoCollection.insertOne(document);
//
//            //一次插入多条数据
//            Document document1 = new Document("user_id","5")
//                    .append("user_name","test")
//                    .append("user_pwd","test1");
//            Document document2 = new Document("user_id","6")
//                    .append("user_name","test")
//                    .append("user_pwd","test2");
//            List<Document> documents = new ArrayList<>();
//            documents.add(document1);
//            documents.add(document2);

            mongoCollection.insertMany(documents);

        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Insert data field！");
        }finally {
            MongoDBConnection.close(conn);
        }
    }

    //插入一条数据
    public <T> void insertData(String databaseName, String collectionName, T data){
        MongoClient conn = null;
        try{
            conn  = MongoDBConnection.getConn();
            MongoDatabase database = conn.getDatabase(databaseName);
            Document document = MongoUtils.toDocument(data);
            MongoCollection mongoCollection = database.getCollection(collectionName);
            mongoCollection.insertOne(document);
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Insert data field！");
        }finally {
            MongoDBConnection.close(conn);
        }
    }


    //删除数据
    public long deleteData(String databaseName, String collectionName, Document document){
        MongoClient conn = null;
        try {
            conn  = MongoDBConnection.getConn();
            MongoDatabase database = conn.getDatabase(databaseName);
            MongoCollection mongoCollection = database.getCollection(collectionName);
//            //删除满足条件的第一条记录
//            mongoCollection.deleteOne(Filters.eq("user_name","test"));
            //删除满足条件的所有数据
//            DeleteResult deleteResult = mongoCollection.deleteMany(Filters.eq(fieldName, value));
            DeleteResult deleteResult = mongoCollection.deleteOne(document);
            long deletedCount = deleteResult.getDeletedCount();
            return deletedCount;
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Delete data field！");
        }finally {
            MongoDBConnection.close(conn);
        }
        return 0;
    }


}
