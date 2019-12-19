package com.jointcorp.otasos.utils;
import java.text.DecimalFormat;
import java.util.List;

/**
 *  数字相关处理类
 * Created by Administrator on 2017/7/11.
 */
public class MathUtil {
    /**
     * 验证是否为数字
     * @return boolean  true:是数字，false：不是数字
     * @param list
     * @author xyc  2015-3-24
     */
    public static boolean matche(List<String> list){
        String pattern = "[0-9]*";
        for(String string : list){
            if(string != null && string.trim().length() != 0 && !string.matches(pattern)){
                return false;
            }
        }
        return true;
    }

    /**
     * 验证是否为数字
     * @return boolean  true:是数字，false：不是数字
     * @author xyc  2015-3-24
     */
    public static boolean matche(String string) {
        String pattern = "[0-9]+";
        if (string != null && !string.matches(pattern)) {
            return false;
        }
        return true;
    }

    /**
     * 保留两位小数
     * @return double
     * @param number
     * @author xyc  2015-4-27
     */
    public static double format(double number){
        DecimalFormat df = new java.text.DecimalFormat("#0.##");
        return Double.parseDouble(df.format(number));
    }

    /**
     * 转换成double
     * @param obj
     * @return
     * @author xyc 2015-5-21上午9:57:15
     */
    public static double toDouble(Object obj){
        String pattern = "^[+-]?\\d+(\\.\\d+)?$";
        if(obj != null){
            String string = obj.toString();
            if(string.matches(pattern)){
                return Double.parseDouble(string);
            }
        }
        throw new NumberFormatException();
    }

    public static void main(String[] args) {

        System.out.println(matche("055"));
    }
}
