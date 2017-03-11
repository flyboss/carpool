package com.carpool.website.controller;

import com.carpool.domain.RoomEntity;
import com.carpool.domain.UserEntity;
import com.carpool.exception.UserNullException;
import com.carpool.website.service.RoomService;
import com.carpool.website.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Project: Carpool
 * Package: com.carpool.website.controller
 * Author:  Novemser
 * 2016/12/2
 */
@Controller
public class UserController {


    private final UserService userService;
    @Autowired
    private RoomService roomService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logIn() {
        return "pages/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "pages/register";
    }

    @GetMapping("/user")
    public String profile(HttpServletRequest request, ModelMap modelMap) {
        request.setAttribute("id", 3);
        request.setAttribute("active", "1");
        String userId = this.userService.getUserIdByCookie(request.getCookies());
        UserEntity userEntity = userService.getUserById(userId);
        modelMap.addAttribute("user", userEntity);
        return "user.profile";
    }

    @GetMapping("/user/edit")
    public String editProfile(ModelMap modelMap, HttpServletRequest request) {
        request.setAttribute("id", 3);
        request.setAttribute("active", "3");
//        Cookie[] cookies=request.getCookies();
        String userId = this.userService.getUserIdByCookie(request.getCookies());
        UserEntity userEntity = userService.getUserById(userId);
        modelMap.addAttribute("user", userEntity);
        return "user.profile.edit";
    }

    @GetMapping("/user/journey")
    public String showJourney(HttpServletRequest request, ModelMap modelMap) {
        request.setAttribute("id", 3);
        request.setAttribute("active", "2");

        String userId = userService.getUserIdByCookie(request.getCookies());
        // 当前用户非法登录 拒绝修改 返回细节界面
        if (null == userId)
            throw new UserNullException("抱歉", "请通过正规渠道登录");

        modelMap.addAttribute("userId", userId);

        List<RoomEntity> roomEntities = roomService.listUserRooms(userId);
        modelMap.addAttribute("rooms", roomEntities);

        UserEntity userEntity = userService.getUserById(userId);
        modelMap.addAttribute("user", userEntity);

        return "user.profile.journey";
    }

    @PostMapping("/user/edit")
    public String edit(ModelMap modelMap, HttpServletRequest request) {
        boolean change = false;
        String userId = this.userService.getUserIdByCookie(request.getCookies());
        // 当前用户非法登录 拒绝修改 返回细节界面
        if (null == userId)
            throw new UserNullException("抱歉", "请通过正规渠道登录");

        UserEntity userEntity = userService.getUserById(userId);
        modelMap.addAttribute("user", userEntity);

        if (request.getParameter("aliPay") != "") {
            userService.updateUserAlipay(userId, request.getParameter("aliPay"));
            change = true;
        }
        if (request.getParameter("QQ") != "") {
            userService.updateUserQQ(userId, request.getParameter("QQ"));
            change = true;
        }
        if (request.getParameter("WeChat") != "") {
            userService.updateUserWeChat(userId, request.getParameter("WeChat"));
            change = true;
        }
        if (change == true)
            return "redirect:/user";
        else
            return "user.profile.edit";
    }

    @PostMapping("/user/password")
    public String editPassword(ModelMap modelMap, HttpServletRequest request) {
        String userId = this.userService.getUserIdByCookie(request.getCookies());
        UserEntity userEntity = userService.getUserById(userId);
        if (userEntity.getPassword() == request.getParameter("currentPassword")) {
            userService.updateUserPassword(userId, request.getParameter("newPassword"));
            return "redirect:/user";
        } else {
            return "user.profile.edit";
        }
    }

    @PostMapping("/user/photo")
    public String editPhoto(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String userId = this.userService.getUserIdByCookie(request.getCookies());
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext().getRealPath("/") + "static/images/"
                        + userId + file.getOriginalFilename();
                file.transferTo(new File(filePath));


                // 复制一份文件到web目录
                // 以免下次部署丢失
                File originFile = new File(filePath);
                //String destFileName = "E:\\MyCodes\\JaveEE\\Carpool\\src\\main\\webapp\\static\\images\\"
                 //       + userId + file.getOriginalFilename();
                //FileUtils.copyFile(originFile, new File(destFileName));

                String photoPath = "/static/images/" + userId + file.getOriginalFilename();
                userService.updateUserPhoto(userId, photoPath);
            } catch (Exception e) {
                e.printStackTrace();
                return "user.profile.edit";
            }
        }
        return "redirect:/user";
    }


}
