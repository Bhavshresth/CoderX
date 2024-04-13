package tech.codingclub.helix.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class ApplicationInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(ApplicationInterceptor.class);

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {


        if (httpServletRequest.getRequestURI().startsWith("/admin")) {
            Boolean isAdmin = ControllerUtils.isUserAdmin(httpServletRequest);
            if (isAdmin != null && isAdmin) {
                return true;
            } else if (!httpServletRequest.getRequestURI().startsWith("/admin/login")) {
                httpServletResponse.sendRedirect("/admin/login");
                return false;
            }
        }


//      When we are trying to login directly through localhost:8080/welcome directly to the
//      dashboard it will intercept the file and the go to the login page as redirected
        if(httpServletRequest.getRequestURI().equals("/welcome"))
        {
            Boolean isUserLogined =ControllerUtils.isUserCodingMafia(httpServletRequest);
            if(isUserLogined!=null && isUserLogined ){
                return true;
            }
            else{
                httpServletResponse.sendRedirect("/login/welcome?failed=1");
                return false;
            }
        }

//      used to block all the incomming user request and place accordingly
        if(httpServletRequest.getRequestURI().startsWith("/user"))
        {
            Boolean isUserLogined =ControllerUtils.isUserCodingMafia(httpServletRequest);
            if(isUserLogined!=null && isUserLogined ){
                return true;
            }
            else{
                if (httpServletRequest.getMethod().equals("GET")) {
                    httpServletResponse.sendRedirect("/login/welcome?failed=1");
                }
                else {
                httpServletResponse.sendError(401);
                }
                return false;
            }
        }


        return true;
    }






    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception)
            throws Exception {
    }
}