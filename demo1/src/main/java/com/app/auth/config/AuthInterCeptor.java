package com.app.auth.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.auth.model.UserToken;
import com.app.auth.util.AuthCookieUtil;

@Component
public class AuthInterCeptor implements HandlerInterceptor {
    
    
    /**
     * 참고 Servlet Life Cycle
     * 
     * Client
     *   - Browser
     * 1. Request 
     *---------------------------------------------------------------------------------------------------
     * Server
     *   - Servlet Container
     * 2. Dispatcher Servlet  <-> 3.Handler Mapping
     *     |  |       
     *     |  |
     *     |  |------------->4.HandlerAdapter <-> (1) Interceptor [preHandle]
     *     |                                  <-> (2) Conterller  <-> Service <-> Repository <-> DB
     *     |                                  <-> (3) Interceptor [postHandle]
     *     |                                  
     *     |--> 5. View Resolver
     *     
     *---------------------------------------------------------------------------------------------------
     * 3. Controller method 정보 Get
     * 4. Handler Adapter는 전달받은 Controller method를 실행하는데, 
     *    실행하기 전에 HandlerInterceptorAdapter를 구현한 interceptor들을 먼저 실행
     * 5. Dispatcher Servlet은 Handler Adapter로부터 응답받은 ViewName과 Model을 View Resolver에게 위임하여,
     *    response body가 될 view(html)를 응답 받음.
     * 
     * 
     */
    
    // Session 검증
    // public static final String SESSION_ID = "SessionID";
    
    /* preHandle : Controller가 호출되기 전 수행 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        /* print request */
        // this.printRequest(request);
       

        // Access 토큰이 필요한 경우 여기로 오게 된다.
        UserToken userToken = AuthCookieUtil.getUserTocken(request);
        if ( userToken == null ) {
            response.sendError(401);
            return false; 
        }
        
        // Session 검증
        // HttpSession httpSession = request.getSession();
        // String sessionItem = (String)httpSession.getAttribute(SESSION_ID);
        //if ( sessionItem == null ) {
        //    return false;
        //    
        //}
        
        return true;
    }

    
    /* postHandle : Controller가 완료된 이후에 수행 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    /* afterCompletion : Controller 수행 후 view단 작업까지 완료 된 후 호출 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
    
    /** ----------------------------------------------------------------------------------------------------  */
    
    // TEST 로그
    private void printRequest(HttpServletRequest request) {
        Enumeration<String>  e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String string = (String) e.nextElement();
            System.out.println("request header:"+string);
        }
    }
    
}
