package com.app.auth.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.auth.model.UserToken;
import com.app.base.util.CODE;
import com.app.base.util.DateUtil;
import com.app.base.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthCookieUtil {
    
    // Tocken 만료시간
    public static final int SECOND = 60;
    public static final int LIMIT_MINUTE = 5; // 5분
    
    public static final String AUTH_COOKIE_KEY = "d-auth";
    // hash 알고리즘 선택
    private static final String ALGOLISM = "HmacSHA256";
    // hash 암호화 key
    private static final String key = "DemoSample";
    
    public static void addAuthCookie(HttpServletResponse response, UserToken user) throws JsonProcessingException, UnsupportedEncodingException {
        
        
        user.setIssued_at ( String.valueOf( DateUtil.getTimeInMillis() ));
        user.setExpires_at( String.valueOf( DateUtil.addTimeInMillis(LIMIT_MINUTE * SECOND ) ));
        
        String jwtString = makeJWT(user);
        Cookie authCookie = new Cookie(AUTH_COOKIE_KEY, jwtString);
        
        
        authCookie.setMaxAge(LIMIT_MINUTE * SECOND);
        authCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        authCookie.setHttpOnly(true);
        
        response.addCookie(authCookie);
        
    }
    
    public static void deleteAuthCookie(HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        
        // AUTH_COOKIE_KEY 쿠키만 초기화
        System.out.println("METHOD:deleteAuthCookie");
        Cookie authCookie = new Cookie(AUTH_COOKIE_KEY, "") ;
        authCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        authCookie.setHttpOnly(false);
        authCookie.setMaxAge(0) ;
        response.addCookie(authCookie) ;
    }
    
    private static boolean validAuthCookie(String jwt) {
        System.out.println("METHOD:validAuthCookie");
        String data[] = jwt.split("\\.");
        
        StringBuffer sb = new StringBuffer();
        sb.append(data[0]);
        sb.append(".");
        sb.append(data[1]);
        
        // Signature
        String signature = hget(sb.toString());
        
        if ( signature.equals(data[2]) == false ) return false;
        
        return true;
    }
    
    public static UserToken getUserTocken(HttpServletRequest request) {
        System.out.println("METHOD:getUserTocken");
        UserToken user = null;

        try {
        
            String jwt = "";
            Cookie[] cookies = request.getCookies();
            
            // 쿠키목록 중 인증서 데이터 찾기
            if ( cookies == null ) {
                throw new Exception("Cookie 없음 오류");
            } 
            
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(AUTH_COOKIE_KEY)){
                    jwt = cookie.getValue();
                    // System.out.println(cookie.getMaxAge());
                    break;
                }
            }
            
            if ( jwt == null ) throw new Exception("Tocken 없음 오류");
            
            String data[] = jwt.split("\\.");
            System.out.println("jwt cookie data:"+jwt);
            // System.out.println(jwt.split(".").length);
            // System.out.println("1:" + data[0]);
            // System.out.println("2:" + data[1]);
            // System.out.println("3:" + data[2]);
            
            if ( validAuthCookie(jwt) == false ) throw new Exception("Tocken 검증 오류");
            
            System.out.println(decode(data[1]));
            
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(decode(data[1]), UserToken.class);
            
    //        Map<String, String> map = mapper.readValue(json, Map.class);
    //        Map<String, Object> map = objectMapper.convertValue(decode(data[1]), Map.class);
            
            System.out.println(user.getExpires_at());
            System.out.println(user.getIssued_at());
            
            System.out.println("현재접속시간 : " + DateUtil.millisToDate(DateUtil.getTimeInMillis()));
            System.out.println("토큰만료시간 : " + DateUtil.millisToDate(StringUtil.parseLong(user.getExpires_at())));
            // 현재시간보다 토큰만료시간이 지나면 오류
            if ( DateUtil.getTimeInMillis() > StringUtil.parseLong(user.getExpires_at()) )
                throw new Exception("Tocken 유효기간 만료");
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
            user = null;
        }
        return user;
    }
    
    private static String encode(String data) throws UnsupportedEncodingException {
        
        Encoder encoder = Base64.getUrlEncoder();
        
        String rtnString = encoder.encodeToString(data.getBytes(CODE.DEFAULT_CHARSET));
        System.out.println(rtnString);
        
/* 참고
        System.out.println("DEBUG DECODE----1");
        byte[] targetBytes = data.getBytes("UTF-8");

        // Encoder#encode(byte[] src) :: 바이트배열로 반환
        byte[] encodedBytes = encoder.encode(targetBytes);
        System.out.println(new String(encodedBytes));
        
        // Encoder#encodeToString(byte[] src) :: 문자열로 반환
        String encodedString = encoder.encodeToString(targetBytes);
        System.out.println(encodedString);
        
        // Base64 디코딩 ///////////////////////////////////////////////////
        Decoder decoder = Base64.getUrlDecoder();
        
        // Decoder#decode(bytes[] src) 
        byte[] decodedBytes1 = decoder.decode(encodedBytes);
        // Decoder#decode(String src)
        byte[] decodedBytes2 = decoder.decode(encodedString);
        // 디코딩한 문자열을 표시
        String decodedString = new String(decodedBytes1, "UTF-8");
        System.out.println(decodedString);
        System.out.println(new String(decodedBytes2, "UTF-8"));
*/
        return rtnString;

    }
    
    private static String decode(String data) throws UnsupportedEncodingException {
        
        Decoder decoder = Base64.getUrlDecoder();
        byte[] dataBytes = decoder.decode(data.getBytes(CODE.DEFAULT_CHARSET));
        String rtnString = new String(dataBytes,CODE.DEFAULT_CHARSET);
        return rtnString;
        
    }
    
    // JWT 양식 저장
    /*
       Header
       {
           "arg":"HS256"
           "typ":"JWT"
       }
       Payload
       {
           UserToken To Json
       }
       Signature
       {
           HMACSH256(Base64UrlEncode(Header) + "." + Base64UrlEncode(Payload),secretKey)
           
       }
     */
    private static String makeJWT(UserToken user) {
        
        try {
            Map<String,Object> headerMap = new LinkedHashMap<String,Object>();
            headerMap.put("arg", "HS256");
            headerMap.put("typ", "JWT");
            
            ObjectMapper mapper = new ObjectMapper();
            // 1. Header
            // convert map to JSON string 
            String headerJson = mapper.writeValueAsString(headerMap);
            String headerEncode = encode(headerJson);
            System.out.println("Header json:" + headerJson);
            System.out.println("Header:" + headerEncode);
            
            // 2. Payload
            ObjectMapper objectMapper = new ObjectMapper();
            String payloadJson = mapper.writeValueAsString(user);
            String payloadEncode = encode(payloadJson);
            System.out.println("Payload json:" + payloadJson);
            System.out.println("Payload:" + payloadEncode);
            
            //--------------------------------------------------
            
            StringBuffer sb = new StringBuffer();
            sb.append(headerEncode);
            sb.append(".");
            sb.append(payloadEncode);
            
            System.out.println("1+2:"+sb.toString());
            
            // 3. Signature
            String signature = hget(sb.toString());
            System.out.println("Signature : " + signature);
            
            // Result String
            sb.append(".");
            sb.append(signature);
            
            return sb.toString();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return "";
        
    }
    
    public static String hget(String data) {

        try {
            // hash 알고리즘과 암호화 key 적용
            Mac hasher = Mac.getInstance(ALGOLISM);
            hasher.init(new SecretKeySpec(key.getBytes(CODE.DEFAULT_CHARSET), ALGOLISM));

            // data를 암호화 적용 후 byte 배열 형태의 결과 리턴
            byte[] hash = hasher.doFinal(data.getBytes(CODE.DEFAULT_CHARSET));
           
            return byteToString(hash);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        catch (InvalidKeyException e){
            e.printStackTrace();
        }
        
        return "";
    }
 
    // byte[]의 값을 16진수 형태의 문자로 변환하는 함수
    private static String byteToString(byte[] hash) {
        StringBuffer buffer = new StringBuffer();
 
        for (int i = 0; i < hash.length; i++) {
            int d = hash[i];
            d += (d < 0)? 256 : 0;
            if (d < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toString(d, 16));
        }
        return buffer.toString();
    }

}