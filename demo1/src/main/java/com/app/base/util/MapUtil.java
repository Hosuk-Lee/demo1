package com.app.base.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;

/**
 *
 * @Description Map Util 클래스
 * @author 
 * @since
 * @version 1.0
 *
 *  수정일       수정자     수정내용
 *  ----------   --------   ---------------------------
 *  2021.05.05   이호석
 *
 *
 */

public class MapUtil {

    // private static Logger log = Logger.getLogger(MapUtil.class);


    /**
     * getString
     *
     * @param Object obj
     * @return String
     */
    public static String getString(Object obj){
        String rtnVal = null;

        if(obj != null){
            if(obj instanceof java.lang.String) {
                rtnVal = (String)obj;
            }else{
                rtnVal = obj.toString();
            }
        }

        return rtnVal;
    }
    
    public static String trimString(Object obj){
    	return StringUtils.trim(getString(obj));
    }

    /**
     * getString
     *
     * @param Object obj
     * @return String
     */
    public static String getNvlString(Object obj){
    	String result = "";
    	String str = MapUtil.getString(obj);
    	if(str != null){
    		result = str;
    	}else{
    		result = "";
    	}
        return result;
    }

    /**
     * getInt
     *
     * @param Object obj
     * @return int
     */
    public static int getInt(Object obj){
        int rtnVal = 0;

        if(obj != null){
            if( obj instanceof BigDecimal ){
                return ((BigDecimal)obj).intValue();

            }else if( obj instanceof String ){
            	return Integer.parseInt(getString(obj));
            }else{
                return ((Integer)obj).intValue();
            }
        }

        return rtnVal;
    }

    /**
     * getLong
     *
     * @param Object obj
     * @return long
     */
    public static long getLong(Object obj){
        long rtnVal = 0L;

        if(obj != null){
            if( obj instanceof BigDecimal ){
                return ((BigDecimal)obj).longValue();
            }else{
                return ((Long)obj).longValue();
            }
        }

        return rtnVal;
    }

    /**
     * getFloat
     *
     * @param Object obj
     * @return float
     */
    public static float getFloat(Object obj){
        float rtnVal = 0f;

        if(obj != null){
            if( obj instanceof BigDecimal ){
                return ((BigDecimal)obj).floatValue();
            }else{
                return ((Float)obj).floatValue();
            }
        }

        return rtnVal;
    }

    /**
     * getDouble
     *
     * @param Object obj
     * @return double
     */
    public static double getDouble(Object obj){
        double rtnVal = 0.0;

        if(obj != null){
            if( obj instanceof BigDecimal ){
                return ((BigDecimal)obj).doubleValue();
            }else{
                return ((Double)obj).doubleValue();
            }
        }

        return rtnVal;
    }


    /**
     * printLog
     *
     */
    public static void printLog(List<Map<String, Object>> records){
        if(records != null){
            for (Map<String, Object> map : records) {
                if(map != null){
                    // log.debug(map.toString());
                }
            }
        }
    }
    public static void printLog(Map<String, Object> records){
        if(records != null){
            // log.debug(records.toString());
        	System.out.println(records.toString());
        }
    }
    
    /**
    * Vo를 Map으로 변환
    * @param obj
    * @return
    */
    public static Map<String,Object> convertObjectToMap(Object obj){
        Map<String,Object> map = new HashMap<String,Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
                map.put(fields[i].getName(), fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

}
