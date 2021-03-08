package com.ta.xutiansheng.xtsapp.util;

public class StringIsEmptyUtil {
    public static boolean IsEmpty(String value){
        if ("".equals(value)||value.equals(null)||"".equals(value.replaceAll(" ",""))){
            return true;
        }
        return false;
    }
}
