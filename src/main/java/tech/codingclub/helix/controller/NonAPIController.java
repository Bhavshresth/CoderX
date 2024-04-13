package tech.codingclub.helix.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rishabh
 */
@Controller
@RequestMapping("/")
public class NonAPIController extends BaseController {
    private static final Logger logger = Logger.getLogger(NonAPIController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public
    @ResponseBody
    String testControllerMethod(ModelMap model) {
        logger.info("Test call!");
        return "Test successful!";
    }



//  Used to handle request mapping of the 404 error page
    @RequestMapping(method = RequestMethod.GET, value = "/alien")
    public
    String errorPage(ModelMap model) {
        return "alien";
    }



//    Mostly used for the testing purpose only
@RequestMapping(method = RequestMethod.GET, value = "/uitest")
public
String uiTest(ModelMap model) {
    return "uitest";
}



    //    used to redirect the produced bug to the Base Controller
    @RequestMapping(method = RequestMethod.GET, value = "/bug")
    public String getQuiz(ModelMap model, HttpServletResponse response, HttpServletRequest request) {

        int i=10/0;
        //Here an error occurs and then it gets redirected to error handler in
        // base Controller which is used to handle any try of exception occur
        // which means we have to redirect it to an alien page or an error page
        return "hello";
    }

}
