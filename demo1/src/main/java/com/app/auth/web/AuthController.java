package com.app.auth.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.model.UserLogout;
import com.app.auth.model.UserSignIn;
import com.app.auth.model.UserSignUp;
import com.app.auth.model.UserToken;
import com.app.auth.util.AuthCookieUtil;
import com.app.base.util.CODE;
import com.app.base.util.MapUtil;
import com.app.cust.service.Cust01Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

//    @Autowired
//    private HttpSession session;
//    
//    @Autowired
//    private HttpServletResponse response;
    
    @Resource(name = "cust01Service")
    private Cust01Service cust01Service;
    
    
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signUp")
    public @ResponseBody ResponseEntity<UserSignUp> signUp(@RequestBody UserSignUp user) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("signUp"+user);
            System.out.println("----------------------------");
            System.out.println("1." + user.getNm()           );
            System.out.println("2." + user.getAlias()        );
            System.out.println("3." + user.getPassword()     );
            System.out.println("4." + user.getPhone_number() );
            System.out.println("5." + user.getEmail()        );
            System.out.println("6." + user.getSex()          );
            System.out.println("----------------------------");
            // MapUtil.printLog(inMap);
            
            System.out.println(MapUtil.convertObjectToMap(user));
            rtnMap = cust01Service.signUp(MapUtil.convertObjectToMap(user));
            
            rtnMap.put("result" , CODE.SUCCESS);
            rtnMap.put("msg"    , "회원가입이 완료되었습니다.");
        } catch (RuntimeException re) {
            System.out.println("@@ERROR@@" + re.getMessage());
            re.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , re.getMessage());
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }

        return new ResponseEntity<UserSignUp>(HttpStatus.OK);

    }
    
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/signIn")
    public @ResponseBody Map<String, Object> signIn(
            HttpServletResponse response,
            @RequestBody UserSignIn user) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("signIn:"+user);
            System.out.println("----------------------");
            System.out.println("2." + MapUtil.getNvlString(user.getEmail()));
            System.out.println("3." + MapUtil.getNvlString(user.getPwd()));
            System.out.println("----------------------");
            // MapUtil.printLog(inMap);
            
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("email", user.getEmail() );
            inMap.put("pwd"  , user.getPwd()   );
            
            rtnMap = cust01Service.signIn(inMap);
            System.out.println(response);
            UserToken userToken = new UserToken();
            userToken.setId("ZZ00000001");
            userToken.setEmail(user.getEmail());
            AuthCookieUtil.addAuthCookie(response, userToken);
            
            rtnMap.put("result" , CODE.SUCCESS);
            rtnMap.put("msg"    , "로그인성공.");

        } catch (RuntimeException re) {
            System.out.println("@@ERROR@@" + re.getMessage());
            re.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , re.getMessage());
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }
    
    @ApiOperation(value = "로그아웃", notes = "로그아웃")
    @PostMapping("/logout")
    public @ResponseBody Map<String, Object> logout(
            HttpServletResponse response,
            @ApiParam(value = "email", required = true, example = "MAIL0001@idus.com") @RequestBody UserLogout user) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("logout:"+user);
            System.out.println("----------------------");
            System.out.println("1." + user.getEmail());
            System.out.println("----------------------");
            // MapUtil.printLog(inMap);
            
            Map<String, Object> inMap = new HashMap<String, Object>();
            
            rtnMap = cust01Service.logout(inMap);
            
            AuthCookieUtil.deleteAuthCookie(response);
            
            rtnMap.put("result" , CODE.SUCCESS);
            rtnMap.put("msg"    , "로그아웃성공.");

        } catch (RuntimeException re) {
            System.out.println("@@ERROR@@" + re.getMessage());
            re.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , re.getMessage());
        } 
        catch (Exception e) {
            
            e.printStackTrace();
            rtnMap.put("resultCode", CODE.ERROR);
            rtnMap.put("resultMsg" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }
    
}
