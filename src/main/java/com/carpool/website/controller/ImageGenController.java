package com.carpool.website.controller;

import com.carpool.website.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project: Carpool
 * Package: com.carpool.website.controller
 * Author:  Novemser
 * 2016/12/20
 */
@Controller
public class ImageGenController {

    @Autowired
    private CaptchaService captchaService;


    //登录获取验证码
    @GetMapping("/getLoginCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
                                        HttpServletRequest request) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);

        try {
            captchaService.getRandcode(request, response, "imagecode");// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/index";
    }

    //验证码验证
    @RequestMapping(value = "/checkimagecode")
    @ResponseBody
    public String checkTcode(HttpServletRequest request, HttpServletResponse response) {
        String validateCode = request.getParameter("validateCode");
        validateCode = validateCode.toLowerCase();
        String code = null;
        //1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        //1:获取session验证码的信息
        //String code1 = (String) request.getSession().getAttribute("");
        //2:判断验证码是否正确
        if (null != code)
            code = code.toLowerCase();

        if (!StringUtils.isEmpty(validateCode) && validateCode.equals(code)) {
            return "ok";
        }

        return "error";
    }

}
