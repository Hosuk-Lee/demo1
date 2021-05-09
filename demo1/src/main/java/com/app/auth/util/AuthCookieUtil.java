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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthCookieUtil {
    
    
    public static final String AUTH_COOKIE_KEY = "d-auth";
    // hash 알고리즘 선택
    private static final String ALGOLISM = "HmacSHA256";
    // hash 암호화 key
    private static final String key = "DemoSample";
    
    public static void addAuthCookie(HttpServletResponse response, UserToken user) throws JsonProcessingException, UnsupportedEncodingException {
        
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonData = objectMapper.writeValueAsString(user);
        String jwtString = makeJWT(user);
        Cookie authCookie = new Cookie(AUTH_COOKIE_KEY, jwtString);
        
        authCookie.setMaxAge(10 * 60); // 10 * 60 10분
        authCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(authCookie);
        
    }
    
    public static void deleteAuthCookie(HttpServletResponse response, UserToken user) throws JsonProcessingException, UnsupportedEncodingException {
        
        // 특정 쿠키만 삭제하기
        Cookie authCookie = new Cookie(AUTH_COOKIE_KEY, null) ;
        authCookie.setMaxAge(0) ;
        response.addCookie(authCookie) ;
    }
    
    public static boolean validAuthCookie(HttpServletRequest request) {
        String jwt = "";
        Cookie[] cookies = request.getCookies();
        
        // 쿠키목록 중 인증서 데이터 찾기
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(AUTH_COOKIE_KEY)){
                jwt = cookie.getValue();
                break;
            }
        }
        System.out.println("jwt cookie data:"+jwt);

        return true;
    }
    
    private static String encode(String data) throws UnsupportedEncodingException {
        
        Encoder encoder = Base64.getUrlEncoder();
        
        byte[] dataBytes = encoder.encode(data.getBytes(CODE.DEFAULT_CHARSET));
        String rtnString = encoder.encodeToString(dataBytes);
        System.out.println(rtnString);

        return rtnString;

    }
    
    private static String decode(String data) throws UnsupportedEncodingException {
        
        Decoder decoder = Base64.getUrlDecoder();
        
        byte[] dataBytes = decoder.decode(data);
        String rtnString = new String(dataBytes,CODE.DEFAULT_CHARSET);
        System.out.println(rtnString);

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