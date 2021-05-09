package com.app.cust.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @Description Cust Util 클래스
 * @author 
 * @since
 * @version 1.0
 *
 *  수정일       수정자     수정내용
 *  ----------   --------   ---------------------------
 *  2021.05.06   이호석
 *
 *
 */

public class CustUtil {

    
    public static boolean nmCheck(String nm) {
        boolean rtnBoolean =  Pattern.matches("^[a-zA-Z가-힣]*$", nm);
        return rtnBoolean;
    }
    
    public static boolean aliasCheck(String alias) {
        boolean rtnBoolean =  Pattern.matches("^[a-z]*$", alias);
        return rtnBoolean;
    }
    
    public static boolean passwdCheck(String passwd) {

        // 최소 10자이상
        if ( passwd.length() < 10 ) {
            System.out.println("passwd.length"+passwd.length());
            return false;
        }
        
        // 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함
        Pattern letter = Pattern.compile("[a-z]{1}[A-z]{1}");
        Pattern digit = Pattern.compile("[0-9]{1}");
        Pattern special = Pattern.compile ("[`~@#$%^&*()-+=_]{1}");
        Matcher hasLetter = letter.matcher(passwd);
        Matcher hasDigit = digit.matcher(passwd);
        Matcher hasSpecial = special.matcher(passwd);

        if(hasLetter.find() && hasSpecial.find() &&hasDigit.find()){
            System.out.println("Password Check Ok");
        } else {
            System.out.println(passwd + ":"+hasLetter.find()+ ":"+hasSpecial.find()+ ":"+hasDigit.find());
            return false;
        }

        return true;
    }
    
    public static boolean emailCheck(String email) {
        // \w : 한개의 알파벳 또는 숫자 또는 '_'(언더바) = [a-zA-Z_0-9]
        // +  : 한개이상
        // ?  : 없음 또는 한개
        String regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
        boolean rtnBoolean = Pattern.matches(regExp, email);
        return rtnBoolean;
    }
    
    public static boolean telNoCheck(String telNo) {
        // ^ : 문자열시작
        // [0-9] : 숫자만
        // * : 앞 문자가 없을 수도 있고, 무한정 많을 수도 있음
        // $ : 문자열의종료
        boolean rtnBoolean =  Pattern.matches("^[0-9]*$", telNo);
        return rtnBoolean;
    }
    
    public static boolean sexCheck(String sex) {

        // null 이거나 길이가 0이면 true
        if ( sex == null ) return true;
        if ( sex.length() == 0 ) return true;
        
        // char(1) 보다 크면 false
        if ( sex.length() > 1 ) return false;
        
        // 코드값 검증 M:남성 W:여성 아니면 false 
        if ( !("M".equals(sex) || "W".equals(sex)) ) return false;
        return true;
    }
    
}
