package com.carpool.website.controller;

import com.carpool.configuration.GlobalConstants;
import com.carpool.domain.ChatRecordEntity;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserUnreceivedChatRecord;
import com.carpool.website.dao.ChatRecordRepository;
import com.carpool.website.dao.UserUnreceivedRecordRepository;
import com.carpool.website.service.RoomService;
import com.carpool.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Project: Carpool
 * Package: com.carpool.website.controller
 * Author:  Novemser
 * 2016/12/5
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserUnreceivedRecordRepository userUnreceivedRecordRepository;

    @Autowired
    private ChatRecordRepository chatRecordRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String homePage(HttpServletRequest request,ModelMap modelMap,HttpSession session) {
        return mainPage(request, 0, GlobalConstants.HOME_CARPOOL_PAGE_SIZE, modelMap,session);
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = GlobalConstants.HOME_CARPOOL_PAGE_SIZE_STR) Integer size,
            ModelMap modelMap,HttpSession session) {
        //如果是通过分享链接进来的，直接转向该链接
        if(session.getAttribute("shareLink")!=null)
        {
            String shareLink = (String)session.getAttribute("shareLink");
            session.removeAttribute("shareLink");
            return "redirect:"+shareLink;
        }

        String user = request.getRemoteUser();
        List<UserUnreceivedChatRecord> unreadMsgRec = this.userUnreceivedRecordRepository.findByUserId(user);
        ArrayList<ChatRecordEntity> unreadMsg = new ArrayList<>();
        for(UserUnreceivedChatRecord uuc: unreadMsgRec){
            unreadMsg.add(uuc.getChatRecordEntity());
        }

        Page<RoomEntity> roomEntities = roomService.findRoom(page, size);
        modelMap.addAttribute("roomPage", roomEntities);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pageCount", roomService.getRoomPageCount());
        modelMap.addAttribute("unreadMsgCount", unreadMsgRec.size());
        modelMap.addAttribute("unreadMsg", unreadMsg);

        return "main";
    }

    @GetMapping("test")
    public String get(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String userid = request.getRemoteUser();

        return "pages/test";
    }
}
