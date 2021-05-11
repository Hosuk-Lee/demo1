package com.app.auth.config;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.auth.model.UserToken;
import com.app.auth.util.AuthCookieUtil;

@Component
public class LoginInterCeptor implements HandlerInterceptor {
    
    // Session 검증
    // public static final String SESSION_ID = "SessionID";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /* preHandle : Controller가 호출되기 전 수행 */
        
        /* print request header */
        // Enumeration<String>  e = request.getHeaderNames();
        // while (e.hasMoreElements()) {
        //     String string = (String) e.nextElement();
        //     System.out.println("request header:"+string);
        // }

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
        //    response.sendError(401);
        //    return false;
        //    
        //}
        
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        /* postHandle : Controller가 완료된 이후에 수행 */
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        /* afterCompletion : Controller 수행 후 view단 작업까지 완료 된 후 호출 */
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
}
