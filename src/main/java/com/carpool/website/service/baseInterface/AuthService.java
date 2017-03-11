package com.carpool.website.service.baseInterface;

import com.alibaba.fastjson.JSONObject;

/**
 * Project: Carpool
 * Package: com.carpool.website.service.baseInterface
 * Author:  Novemser
 * 2016/12/14
 */
public interface AuthService {
    boolean verify(String username, String password, JSONObject result);
}
