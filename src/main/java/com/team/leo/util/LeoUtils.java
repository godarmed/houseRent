package com.team.leo.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LeoUtils {

    private LeoUtils(){}
    /**
     * 将字符串转换为日期
     * @param dateStr       日期字符串
     * @param pattern       格式化模板
     * @return
     */
    public static Date string2Date(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }


    /**
     * 将日期转换为字符串
     * @param date          日期
     * @param pattern       格式化模板
     * @return
     */
    public static String Date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = null;
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 根据实体类的属性获取表头
     * @return      表头列表
     */
    public static List<String> getThead(Class entity) {
        //反射获取实体类属性
        Field[] fields = entity.getDeclaredFields();
        //要返回的thead列表
        List<String> thead = new ArrayList<>();
        for (Field field : fields) {
            thead.add(field.getName());
        }
        thead.add("修改");
        thead.add("删除");
        return thead;
    }

    /**
     * 根据实体类的获取成员变量名
     * @return      表头列表
     */
    public static List<String> getFields(Class entity) {
        //反射获取实体类属性
        Field[] fields = entity.getDeclaredFields();
        //要返回的thead列表
        List<String> thead = new ArrayList<>();
        for (Field field : fields) {
            thead.add(field.getName());
        }
        return thead;
    }

    /**
     * 根据实体类的成员变量名获取get方法
     * @return      表头列表
     */
    public static Method getFieldValue(Class entity,String fieldName) {
        //创建对应的get方法名
        String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        //反射获取实体类对象方法
        Method method = null;
        try {
            method = entity.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return method;
    }

    //将各种类型的对象转换为可以用于sql拼接中的字符串
    public static <T extends Object>String field2String(T fieldValue){
        String value = "";
        if(fieldValue instanceof Date){
            return Date2String((Date)fieldValue, "yyyy-MM-dd");
        }else if(!(fieldValue instanceof String)){
            return String.valueOf(fieldValue);
        }
        return (String)fieldValue;
    }
}
