package com.carpool.website.controller;

import com.carpool.exception.InternalErrorException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Project: Carpool
 * Package: com.carpool.website.controller
 * Author:  Novemser
 * 2016/12/15
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "pages/index";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "pages/index";
    }



    @GetMapping("/test")
    public String indexTest() {
        throw new InternalErrorException("2002", "玄学");
//        return "main";
    }
}
