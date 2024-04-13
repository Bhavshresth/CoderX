package tech.codingclub.helix.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;




@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    private static Logger logger = Logger.getLogger(MainController.class);



    //    Intro Page connection with hello.jsp
    @RequestMapping(method = RequestMethod.GET, value = "/helloworld")
    public String signUp(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "hello";
    }

//Here Mainly if the user is logged in then the page welcome will be shown and then if the cookies are deleted then the welcomelogin is showed




    //  SignUp Page connection with signup.jsp with GET request
    @RequestMapping(method = RequestMethod.GET, value = "/signup")
    public String getQuiz(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "signup";
    }



    //  SignUp Page connection with signup.jsp with POST request and send the incoming request to the server
    @RequestMapping(method = RequestMethod.POST, value = "/signup")//API is responsible for handling post request of ajax
    public
    @ResponseBody
    SignupResponse signUpData(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response) {
        boolean user_created= false;
        String message="";
        if( GenericDB.getCount(tech.codingclub.helix.tables.Member.MEMBER,tech.codingclub.helix.tables.Member.MEMBER.EMAIL.eq(member.email)) > 0) {
            message="User exist for this email";
        }
        else{
            member.role ="cm";
            new GenericDB<Member>().addRow(tech.codingclub.helix.tables.Member.MEMBER, member);
            user_created=true;
            message="User Created!";
        }
        return new SignupResponse(message,user_created);
    }




    // this responsive tag "/api/time" used to return the current time
    @RequestMapping(method = RequestMethod.GET, value = "/api/time")
    public @ResponseBody String getTime(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {

        TimeApi timeApi=new TimeApi(new Date().toString(),new Date().getTime());
        return new Gson().toJson(timeApi);
    }




    // This is used to connect the controller to the wiki fetcher ... used to show the given fetched result in Gson format
    @RequestMapping(method = RequestMethod.GET, value = "/api/wiki")
    public @ResponseBody String getWikiResultGson(ModelMap modelMap,@RequestParam("info") String info , HttpServletResponse response, HttpServletRequest request) {

        WikipediaDownloader wikipediaDownloader=new WikipediaDownloader(info);
        WikiResult x= wikipediaDownloader.getResult();
        return new Gson().toJson(x);
    }





    // This is used to connect the controller to the wikiapi.jsp file ... used to show the given fetched result in Gson format
    @RequestMapping(method = RequestMethod.GET, value = "/api/wiki/html")
    public String getWikiResultHtml(ModelMap modelMap,@RequestParam("info") String info , HttpServletResponse response, HttpServletRequest request) {

        WikipediaDownloader wikipediaDownloader=new WikipediaDownloader(info);
        WikiResult x=wikipediaDownloader.getResult();

        modelMap.addAttribute("IMAGE", x.getImage_url());
        modelMap.addAttribute("DESCRIPTION", x.getText_result());

        return "wikiapi";
    }





    @RequestMapping(method = RequestMethod.POST, value = "/handle")
    public
    @ResponseBody
    String handleEncrypt(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}