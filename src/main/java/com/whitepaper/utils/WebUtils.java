package com.whitepaper.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 把Map中的值注入到对应到JavaBean属性中
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String s, int defaultValue){
        if(s!= null && !"".equals(s))
            defaultValue = Integer.parseInt(s);
        return defaultValue;
    }
}
