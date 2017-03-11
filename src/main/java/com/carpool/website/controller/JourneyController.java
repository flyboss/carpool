package com.carpool.website.controller;

import com.carpool.domain.JourneyEntity;
import com.carpool.exception.PermissionDeniedException;
import com.carpool.exception.UserNullException;
import com.carpool.website.model.JourneyCommentDetail;
import com.carpool.website.model.MyTrack;
import com.carpool.website.service.JourneyService;
import com.carpool.website.service.RoomService;
import com.carpool.website.service.UserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by qi on 2016/12/1.
 */

@Controller
@RequestMapping(value = "/journey")
public class JourneyController {


    @Autowired
    private JourneyService journeyService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/getMyJourneyAsHost",method = RequestMethod.GET)
    public String getMyJourneyAsHost(@RequestParam( value = "currentPage" ,defaultValue = "0")Integer currentPage, ModelMap modelMap,HttpServletRequest request)
    {
        String userid = userService.getUserIdByCookie(request.getCookies());
        if(userService.getUserById(userid)==null)
            throw new UserNullException("EORROR","不存在的用户");
        Page<JourneyEntity> journeyEntities = journeyService.getMyJourneyAsHost(userid,currentPage);
        modelMap.addAttribute("userId",userid);
        modelMap.addAttribute("journeys",journeyEntities);
        modelMap.addAttribute("currentPage",currentPage);
        modelMap.addAttribute("type","我是房主");
        return "journey/journeys";
    }

    @RequestMapping(value = "/getAllJourneys",method = RequestMethod.GET)
    public String getAllJourneys(@RequestParam(value = "currentPage",defaultValue = "0") Integer currentPage,ModelMap modelMap,HttpServletRequest request)
    {
        String userid = userService.getUserIdByCookie(request.getCookies());
        Page<JourneyEntity> journeyEntities = journeyService.getAllJourneys(userid,currentPage);
        modelMap.addAttribute("userId",userid);
        modelMap.addAttribute("journeys",journeyEntities);
        modelMap.addAttribute("currentPage",currentPage);
        modelMap.addAttribute("type","全部出行");
        return "journey/journeys";
    }

    @RequestMapping(value = "/getJourneyCommentDetail/{journeyId}",method = RequestMethod.GET)
    @ResponseBody
    public List<JourneyCommentDetail> getJourneyCommentDetail(@PathVariable("journeyId")Integer journeyId, @RequestParam("userid")String userid)
    {
        return journeyService.getJourneyCommentDetails(journeyId,userid);
    }


    @RequestMapping(value = "/getMyTrack",method = RequestMethod.GET)
    public String getMyTrack(ModelMap modelMap, HttpServletRequest request)
    {
        String userId = userService.getUserIdByCookie(request.getCookies());
        List<MyTrack> myTracks = journeyService.getMyTrack(userId);
        modelMap.addAttribute("mytracks",myTracks);
        return "journey/myTrack";
    }

    //如果生成行程成功，返回'true'
    @RequestMapping(value = "/generateJourneyFromRoom",method = RequestMethod.GET)
    @ResponseBody
    public String generateJourneyFromRoom(@RequestParam("roomId")Integer roomId,HttpServletRequest httpRequest)
    {
        String userId = userService.getUserIdByCookie(httpRequest.getCookies());
        if(userId == null)
            throw  new PermissionDeniedException(HttpStatus.SC_FORBIDDEN + "","你没有权限生成行程,你还没有登录!");
        if(journeyService.generateJourneyFromRoomId(roomId,userId)==true)
            return  "true";
        return "false";
    }
}
