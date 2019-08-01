package edu.sjtu.common.util;

import java.util.UUID;

public class UUIDGenerator {
    /**
     * 获得一个UUID
     * String UUID
     */
    public static String getUUID(){
        StringBuilder sb=new StringBuilder();
        //拼接当前日期
        sb.append(DateUtil.getToday().substring(0,8));
        //拼接UUID 去除“-”，取前12位
        sb.append(UUID.randomUUID().toString().replace("-","").substring(0,12));
        return sb.toString();
    }
    /**
     * 获得指定数目的UUID
     * number int 需要获得的UUID数量
     * String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] ss = new String[number];
        for(int i=0;i<number;i++){
            ss[i] = getUUID();
        }
        return ss;
    }
}

