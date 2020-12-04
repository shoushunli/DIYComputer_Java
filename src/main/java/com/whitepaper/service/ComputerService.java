package com.whitepaper.service;

import com.whitepaper.pojo.ListOfComputer;

import java.util.List;

public interface ComputerService {

    /**
     * 根据用户选项生成初步清单，不包含总价信息
     * @param cpuType
     * @param gpuType
     * @param memoryType
     * @param powerType
     * @param pccAndMotherboardType
     * @param ssdType
     * @param hddType
     * @return
     */
    public List<ListOfComputer> queryForListOfComputer(String cpuType, String gpuType, String memoryType, String powerType, String pccAndMotherboardType, String ssdType, String hddType);


    /**
     * 根据最大金额和清单数量的限制对初步清单进行筛选和排序
     * @param list
     * @param maximum
     * @param number
     * @return
     */
    public List<ListOfComputer> recommandList(List<ListOfComputer> list, Double maximum, Integer number);

    /**
     * comparator的辅助函数，将double转成int
     * @param n
     * @return
     */
    public int sign(double n);

    /**
     * 数组转成list
     * @param arr
     * @param <T>
     * @return
     */
    public <T> List<T> arrayToList(T[] arr);


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


