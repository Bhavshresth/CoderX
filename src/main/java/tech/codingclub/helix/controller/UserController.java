package tech.codingclub.helix.controller;

import org.apache.log4j.Logger;
import org.jooq.Condition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.*;
import tech.codingclub.helix.global.SysProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = Logger.getLogger(UserController.class);


    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public String userWelcome(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        Member member=ControllerUtils.getCurrentMember(request);

        //Access only when the user is logged in!

        return "welcome";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/public-tweet/{id}")
    public
    @ResponseBody
    List<TweetUI> fetchTweet(@PathVariable("id")  Long id, HttpServletRequest request, HttpServletResponse response) {

        Condition condition=tech.codingclub.helix.tables.Tweet.TWEET.ID.lessThan(id);
        List<Tweet> data= (List<Tweet>) GenericDB.getRows(tech.codingclub.helix.tables.Tweet.TWEET, Tweet.class,condition,3,tech.codingclub.helix.tables.Tweet.TWEET.CREATED_AT.desc());

        Set<Long> memberIds=new HashSet<Long>();
        for(Tweet tweet: data){
            memberIds.add(tweet.author_id);
        }
        HashMap<Long,Member> memberHashMap=new HashMap<Long, Member>();
        Condition condition1=tech.codingclub.helix.tables.Member.MEMBER.ID.in(memberIds);
        List<Member> members= (List<Member>) GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER, Member.class,condition1,null);

        for(Member member : members){
            memberHashMap.put(member.id, member);
        }


        ArrayList<TweetUI>tweetUIS=new ArrayList<TweetUI>();
        for(Tweet tweet:data){

            Member member=memberHashMap.get(tweet.author_id);
            TweetUI tweetUI=new TweetUI(tweet,member);
            tweetUIS.add(tweetUI);

        }

        return tweetUIS;
    }



    @RequestMapping(method = RequestMethod.POST, value = "/create-post")
    public
    @ResponseBody
    String createTweet(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        Long t1=ControllerUtils.getUserId(request);
        Tweet tweet=new Tweet(data,null,new Date().getTime(),t1);
        new GenericDB<Tweet>().addRow(tech.codingclub.helix.tables.Tweet.TWEET, tweet);

        return "Posted Successfully";
    }



    @RequestMapping(method = RequestMethod.GET, value = "/recommendations")
    public String welcome(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        Member member=ControllerUtils.getCurrentMember(request);

        List<Member> members= (List<Member>) GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER, Member.class,null ,10 ,tech.codingclub.helix.tables.Member.MEMBER.ID.desc());
        Long currentMemberId=ControllerUtils.getUserId(request);
        ArrayList<Long> memberIds=new ArrayList<Long>();
        for(Member m: members){
            memberIds.add(m.id);
        }

        Condition condition=tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(member.id).and(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID.in(memberIds));
        List<Follower> followerRows= (List<Follower>) GenericDB.getRows(tech.codingclub.helix.tables.Follower.FOLLOWER, Follower.class,condition,null);

        Set<Long> followedMemberIds=new HashSet<Long>();

        for(Follower follower:followerRows){
            followedMemberIds.add(follower.following_id);
        }
        for(Member memberTemp: members){
            if(followedMemberIds.contains(memberTemp.id)){
                memberTemp.is_followed=true;
            }
        }

        //filter off!
        //Access only when the user is logged in!
        modelMap.addAttribute("USER_IMAGE", "/images/profile-image/"+currentMemberId+".jpeg");
        modelMap.addAttribute("NAME", member.name);
        modelMap.addAttribute("RECOMMENDATIONS", members);

        return "recommendations";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/followed")
    public String followed(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        Long currentMemberId=ControllerUtils.getUserId(request);

        Condition condition=tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(currentMemberId);
        List<Long> followedIds=new GenericDB<Long>().getColumnRows(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID,tech.codingclub.helix.tables.Follower.FOLLOWER,Long.class,condition,100);

        Condition selectMemberCondition=tech.codingclub.helix.tables.Member.MEMBER.ID.in(followedIds);
        List<Member> followMembers= (List<Member>) GenericDB.getRows(tech.codingclub.helix.tables.Member.MEMBER, Member.class,selectMemberCondition,10 ,tech.codingclub.helix.tables.Member.MEMBER.ID.desc());

        modelMap.addAttribute("USER_IMAGE", "/images/profile-image/"+currentMemberId+".jpeg");
        modelMap.addAttribute("FOLLOWED", followMembers);
        //Access only when the user is logged in!
        return "followed";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public String updateUser(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {

        return "updateuser";
    }

    private static String saveUploadedFile( MultipartFile file, Long userId){
        try {
            String path = SysProperties.getBaseDir()+"/images/profile-image/"+userId+".jpeg";
            file.transferTo( new File(path));
            return "/images/profile-image/"+userId+".jpeg";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/profile-image/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile, HttpServletRequest request) {
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        String path = "";
        try {
            Long currentMemberId = ControllerUtils.getUserId(request);
            path = saveUploadedFile(uploadfile,currentMemberId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(path, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/follow-member/{member_id}")
    public
    @ResponseBody
    String followMember(@PathVariable("member_id") Long memberId, HttpServletRequest request, HttpServletResponse response) {

        Long currentUserId = ControllerUtils.getUserId(request);
        if (currentUserId != null && memberId != null && !currentUserId.equals(memberId)) {
            Follower follower = new Follower(currentUserId,memberId);
            new GenericDB<Follower>().addRow(tech.codingclub.helix.tables.Follower.FOLLOWER, follower);
            return "Connected successfully";
        }
        else{
            return "Not permitted";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/un-follow-member/{member_id}")
    public
    @ResponseBody
    String unfollowMember(@PathVariable("member_id") Long memberId, HttpServletRequest request, HttpServletResponse response) {

        Long currentUserId = ControllerUtils.getUserId(request);
        if (currentUserId != null && memberId != null && !currentUserId.equals(memberId)) {
         //Delete a relation by currentUserId & memberId

            Condition condition=tech.codingclub.helix.tables.Follower.FOLLOWER.USER_ID.eq(currentUserId).and(tech.codingclub.helix.tables.Follower.FOLLOWER.FOLLOWING_ID.eq(memberId));
            boolean success=GenericDB.deleteRow(tech.codingclub.helix.tables.Follower.FOLLOWER, condition);
            if (success) return "Unfollowed successfully";
        }
        else{
            return "Not permitted";
        }
        return "Backend error";
    }


}