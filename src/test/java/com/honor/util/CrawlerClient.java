package com.honor.util;


import com.honor.parameter.P;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rongyaowen on 2018/10/4.
 */
public class CrawlerClient {

    /**
     * 第一个爬虫程序，获取源码，注意需要带上User_Agetn
     */
    @Test
    public void crawlerClient_01() {
        String url = "https://www.douban.com";
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(P.REQUEST.USER_AGENT, P.USER_AGENT);
        Map<String, Object> resMap = Request.get(url, headerParams);
        System.out.println(resMap.get(P.REQUEST.RES_BODY));
    }


//    @Test
//    public void get() throws Exception {
//        System.out.println(P.COOKIE);
//        String url = "https://www.douban.com";
//        String resStr = Request.get(url);
//        System.out.println(resStr);
//    }
//
//    @Test
//    public void post() throws Exception {
////        String formDataStr = "source=index_nav&form_email=572491392%40qq.com&form_password=douban753159&captcha-solution=school&captcha-id=qIXn45inOJui2weLg5TC2hss%3Aen";
//        String authCodeUrl = "https://www.douban.com/j/misc/captcha";
//        String resStr = Request.get(authCodeUrl);
//        System.out.println(resStr);
//        ReturnMessage returnMessage = GsonUtil.fromJson(resStr, ReturnMessage.class);
//        System.out.println("验证码链接：" + "https:" + returnMessage.getUrl());
//        System.out.println("token:" + returnMessage.getToken());
//
//        String formEmail = "572491392@qq.com";
//        String fromPassword = "douban753159";
//        String captchaSolution = "";
//        String captchaId = returnMessage.getToken();
//
//        String formDataStr = "source=index_nav" +
//                "&form_email=" + formEmail +
//                "&form_password=" + fromPassword +
//                "&captcha-solution=" + captchaSolution +
//                "&captcha-id=" + captchaId;
//
//        System.out.println(formDataStr);
//
//    }
//
//    @Test
//    public void login() {
//        String authCode = "language";
//        String captchaId  = "vodyh1phQLB1CBbcpWOv3ANW:en";
//
//
//        String formDataStr = "source=index_nav" +
//                "&form_email=572491392@qq.com" +
//                "&form_password=douban753159" +
//                "&captcha-solution="+authCode +
//                "&captcha-id="+captchaId;
//        System.out.println(formDataStr);
//        String url = "https://www.douban.com/accounts/login";
//        Map<String,Object> resMap = Request.post(url, formDataStr);
//        System.out.println(resMap.get("cookie"));
//        System.out.println(P.COOKIE +  ";" +resMap.get("cookie"));
//
//
//    }
}
