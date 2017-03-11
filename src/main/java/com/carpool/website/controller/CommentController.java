package com.carpool.website.controller;

import com.carpool.domain.CommentEntity;
import com.carpool.domain.UserEntity;
import com.carpool.exception.PermissionDeniedException;
import com.carpool.exception.UserNullException;
import com.carpool.website.model.Comment;
import com.carpool.website.service.CommentService;
import com.carpool.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by qi on 2016/11/30.
 */

@Controller
@RequestMapping("/comment")
//@SessionAttributes("userid")
//@SessionAttributes("userid")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/getSendedComment")
    public String getComments()
    {
        return  "comment/sendedComments";
    }

    @RequestMapping(value = "/getReceivedComment",method = RequestMethod.GET)
    public String getReceivedComment(@RequestParam(value = "currentPage",defaultValue = "0")Integer currentPage, ModelMap modelMap, HttpServletRequest request)
    {
        String userid = userService.getUserIdByCookie(request.getCookies());
        UserEntity userEntity = userService.getUserById(userid);
        Page<CommentEntity> commentEntities = commentService.getRecievedComment(userid,currentPage);
        modelMap.addAttribute("receivedComments",commentEntities);
        modelMap.addAttribute("currentPage",currentPage);
        modelMap.addAttribute("userid",userid);
        modelMap.addAttribute("user",userEntity);
        return  "comment/receivedComments";
    }

    @RequestMapping(value = "/getSendedComment",method = RequestMethod.GET)
    public String getSendedComment(@RequestParam(value = "currentPage",defaultValue = "0")Integer currentPage, ModelMap modelMap,HttpServletRequest request)
    {
        String userid = userService.getUserIdByCookie(request.getCookies());
        Page<CommentEntity> commentEntities = commentService.getSendedComment(userid,currentPage);
        modelMap.addAttribute("userid",userid);
        modelMap.addAttribute("sendedComments",commentEntities);
        modelMap.addAttribute("currentPage",currentPage);
        return  "comment/sendedComments";
    }

    @RequestMapping(value = "/getOthersComment/{otherUserId}",method = RequestMethod.GET)
    public String getOthersComment(@PathVariable("otherUserId")String otherUserid,@RequestParam( value = "currentPage",defaultValue = "0") Integer currentPage,ModelMap modelMap,HttpServletRequest request)
    {
        UserEntity userEntity = userService.getUserById(otherUserid);
        Page<CommentEntity> commentEntities = commentService.getRecievedComment(otherUserid,currentPage);
        modelMap.addAttribute("otherUser",userEntity);
        modelMap.addAttribute("comments",commentEntities);
        return "comment/lookOtherComments";
    }

    @RequestMapping(value = "/makeComment")
    @ResponseBody
    public String addComment(Comment comment)
    {
        if(commentService.addComment(comment))
            return "success";
        return "failed";
    }
}
