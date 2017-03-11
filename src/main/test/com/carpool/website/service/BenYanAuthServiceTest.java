package com.carpool.website.service;

import com.alibaba.fastjson.JSONObject;
import com.carpool.website.service.baseInterface.AuthService;
import org.junit.Test;

/**
 * Project: Carpool
 * Package: com.carpool.website.service
 * Author:  Novemser
 * 2016/12/14
 */
public class BenYanAuthServiceTest {
    @Test
    public void verify() throws Exception {
        AuthService service = new BenYanAuthService();

        JSONObject res = new JSONObject();
        boolean valid = service.verify("1452681", "XXXXXXX", res);

        System.out.println(valid + ":" + res);
    }

}