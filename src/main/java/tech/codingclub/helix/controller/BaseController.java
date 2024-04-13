package tech.codingclub.helix.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


public class BaseController {



//    Mostly used to handle Exception and show to 404 error page without showing the page information to the user
    @ExceptionHandler
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        System.out.println(e.getMessage());
        return "alien";
    }
}
