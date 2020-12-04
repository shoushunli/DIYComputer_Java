package com.whitepaper.dao;

import com.whitepaper.pojo.ListOfComputer;

import java.util.List;

public interface ComputerDao {
    /**
     * 想mongodb的computer数据库的computer集合里面添加一条数据
     * @param listOfComputer
     */
    public void addComputer(ListOfComputer listOfComputer);

    /**
     * 根据sku将mongodb的computer数据库的computer集合里面对应的数据删除
     * @param sku
     */
    public long deleteComputer(ListOfComputer computer);

    /**
     * 将mongodb的computer数据库的computer集合里面的数据转成对象放入list集合
     * @return
     */
    public List<ListOfComputer> showData();
}
