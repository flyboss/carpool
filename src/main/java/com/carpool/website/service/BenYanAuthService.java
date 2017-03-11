package com.carpool.website.service;

import com.alibaba.fastjson.JSONObject;
import com.carpool.website.service.baseInterface.AuthService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project: Carpool
 * Package: com.carpool.website.service
 * Author:  Novemser
 * 2016/12/14
 */
@Service
public class BenYanAuthService implements AuthService {
    private Map<String, String> cookies4m3 = new HashMap<>();
    private Map<String, String> postData = new HashMap<>();
    private Map<String, String> cookiesIds = new HashMap<>();

    private final String step2 = "http://4m3.tongji.edu.cn/eams/login.action";
    private final String step1 = "http://4m3.tongji.edu.cn/";
    private final String step3 = "http://4m3.tongji.edu.cn/eams/samlCheck";

    @Override
    public boolean verify(String username, String password, JSONObject result) {

        try {
            Connection connection = Jsoup.connect(step1);
            Connection.Response response = connection.execute();
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookies4m3.put(entry.getKey(), entry.getValue());
            }

            connection = Jsoup.connect(step2);
            connection.cookie("SERVERNAME", cookies4m3.get("SERVERNAME"));

            response = connection.execute();
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookies4m3.put(entry.getKey(), entry.getValue());
            }

            connection = Jsoup.connect(step3);
            for (Map.Entry<String, String> entry : cookies4m3.entrySet()) {
                connection.cookie(entry.getKey(), entry.getValue());
            }

            response = connection.execute();
            Document doc = response.parse();
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookies4m3.put(entry.getKey(), entry.getValue());
            }

            String step2 = doc.select("meta").attr("content").substring(6);

            connection = Jsoup
                    .connect(step2);

            response = connection.execute();
            doc = response.parse();
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookiesIds.put(entry.getKey(), entry.getValue());
            }

            String step3 = "https://ids.tongji.edu.cn:8443" + doc.select("form").attr("action");

            connection = Jsoup
                    .connect(step3)
                    .method(Connection.Method.POST);
            for (Map.Entry<String, String> entry : cookiesIds.entrySet()) {
                connection.cookie(entry.getKey(), entry.getValue());
            }

            response = connection.execute();
            doc = response.parse();
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookiesIds.put(entry.getKey(), entry.getValue());
            }

            String step4 = doc.select("form").attr("action");

            postData.put("option", "credential");
            postData.put("Ecom_User_ID", username);
            postData.put("Ecom_Password", password);
            postData.put("submit", "登录");

            connection = Jsoup
                    .connect(step4)
                    .method(Connection.Method.POST)
                    .data(postData);
            for (Map.Entry<String, String> entry : cookiesIds.entrySet()) {
                connection.cookie(entry.getKey(), entry.getValue());
            }

            response = connection.execute();
            String webPage = response.parse().html();
            Pattern pattern = Pattern.compile("top.location.href=\'(.*?)\'");
            Matcher matcher = pattern.matcher(webPage);
            for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                cookiesIds.put(entry.getKey(), entry.getValue());
            }

            if (matcher.find()) {
                String step5 = matcher.group(1);

                connection = Jsoup.connect(step5);
                for (Map.Entry<String, String> entry : cookiesIds.entrySet()) {
                    connection.cookie(entry.getKey(), entry.getValue());
                }
                response = connection.execute();
                doc = response.parse();

                for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                    cookiesIds.put(entry.getKey(), entry.getValue());
                }

                String step6 = doc.select("form").attr("action");

                String SAMLResponse = doc.select("form").select("input").get(0).attr("value");
                String RelayState = doc.select("form").select("input").get(1).attr("value");

                postData.clear();
                postData.put("SAMLResponse", SAMLResponse);
                postData.put("RelayState", RelayState);
                cookies4m3.put("oiosaml-fragment", "");

                connection = Jsoup
                        .connect(step6)
                        .data(postData)
                        .method(Connection.Method.POST);
                for (Map.Entry<String, String> entry : cookies4m3.entrySet()) {
                    connection.cookie(entry.getKey(), entry.getValue());
                }
                // 一定要执行最后一步
                // 以使服务器确认完成认证
                doc = connection.execute().parse();
                String nameText = doc.body().select("form a").text();
                result.put("nameWithId", nameText);
                String name = nameText.split("\\(")[0];
                result.put("name", name);
                return true;
            }
            return false;

        } catch (Exception e) {
            result.put("error", e.getMessage());
            return false;
        }
    }
}
