package com.app.auth.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.app.base.config.DemoServiceException;
import com.app.base.util.CODE;
import com.app.base.util.MapUtil;
import com.app.cust.service.Cust01Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AuthResMessage.auth_200),
            @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
            @ApiResponse(code = 500, message = "서버 오류"),

        })
    @PostMapping("/signUp")
    public @ResponseBody Map<String, Object> signUp(@RequestBody UserSignUp user) {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        try {
            
            System.out.println("signUp"+user);
            System.out.println("----------------------------");
            System.out.println("1." + user.getNm()           );
            System.out.println("2." + user.getAlias()        );
            System.out.println("3." + user.getPwd()          );
            System.out.println("4." + user.getTelNo()        );
            System.out.println("5." + user.getEmail()        );
            System.out.println("6." + user.getSex()          );
            System.out.println("----------------------------");
            // MapUtil.printLog(inMap);
            
            System.out.println(MapUtil.convertObjectToMap(user));
            Map<String, Object> inMap = MapUtil.convertObjectToMap(user);
            rtnMap = cust01Service.signUp(inMap);
            
            
            rtnMap.put("result" , CODE.SUCCESS);
            rtnMap.put("message", "");
        } catch (DemoServiceException e) {
            e.printStackTrace();
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // 시스템 오류 개선사항 로깅해야됨.
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }

        return rtnMap;

    }
    
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AuthResMessage.auth_200),
            @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
            @ApiResponse(code = 500, message = "서버 오류"),

        })
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
            
            cust01Service.signIn(inMap);
            
            System.out.println(response);
            UserToken userToken = new UserToken();
            userToken.setId("ZZ00000001");
            userToken.setEmail(user.getEmail());
            AuthCookieUtil.addAuthCookie(response, userToken);
            
            cust01Service.updateCustCookie(inMap);
            
            rtnMap.put("result" , CODE.SUCCESS);
            rtnMap.put("message", "");

        } catch (DemoServiceException e) {
            e.printStackTrace();
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }
    
    @ApiOperation(value = "로그아웃", notes = "로그아웃")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AuthResMessage.auth_200),
            @ApiResponse(code = 401, message = "인증 오류 (로그인 만료, 토큰 인증오류 등)"),
            @ApiResponse(code = 500, message = "서버 오류"),

        })
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
            rtnMap.put("message", "");

        } catch (DemoServiceException e) {
            e.printStackTrace();
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("result", CODE.ERROR);
            rtnMap.put("message" , CODE.SYSTEM_ERROR_MESSAGE);
            
        }
        return rtnMap;
    }
    
}
