package com.honor.util;

import com.honor.parameter.P;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by rongyaowen on 2018/10/4.
 * 请求封装，get请求，post请求。
 */
public class Request {
    private static CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

    /**
     * get 请求
     *
     * @param url
     * @param headerParams 请求头
     * @return
     */
    public static Map<String, Object> get(String url, Map<String, Object> headerParams) {
        HttpGet httpGet = new HttpGet(url);
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("请求链接", url);
        return response(httpGet, headerParams, logMap);
    }

    /**
     * post 请求
     *
     * @param url
     * @param headerParams  请求头
     * @param requestParams 请求数据
     * @return
     */
    public static Map<String, Object> post(String url, Map<String, Object> headerParams, Map<String, Object> requestParams) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = null;
        try {
            String requestParamsStr = null;
            if (!requestParams.isEmpty() && !StringUtils.isEmpty(requestParamsStr = requestParams.get(P.REQUEST.REQUEST_PARAMS).toString())) {
                entity = new StringEntity(requestParamsStr);
            }
            String contentTypeStr = null;
            if (!requestParams.isEmpty() && !StringUtils.isEmpty(contentTypeStr = requestParams.get(P.REQUEST.CONTENT_TYPE).toString())) {
                // 表单格式数据
                entity.setContentType(contentTypeStr);
            }
            httpPost.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("亲求链接", url);
        logMap.put("请求参数", requestParams);

        return response(httpPost, headerParams, logMap);
    }

    /**
     * 请求
     *
     * @param httpRequestBase
     * @param headerParams    请求头
     * @param logMap          日志map
     * @return
     */
    private static Map<String, Object> response(HttpRequestBase httpRequestBase, Map<String, Object> headerParams, Map<String, Object> logMap) {
        Map<String, Object> resMap = new HashMap<>();
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
                .setSocketTimeout(5000).build();
        httpRequestBase.setConfig(config);
        // 拼装请求头
        if (!headerParams.isEmpty()) {
            for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
                httpRequestBase.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        try {
            HttpResponse httpResponse = closeableHttpClient.execute(httpRequestBase);
            // 状态码
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            logMap.put("请求头", headerParams);
            logMap.put("状态码", statusCode);
            logMap.put("请求方法", httpRequestBase.getMethod());
            LogUtil.debug(LogUtil.mapToStr(logMap));

            // 返回响应body数据
            HttpEntity entity = httpResponse.getEntity();
            String resBody = EntityUtils.toString(entity, "utf-8");
            // 响应头
            Header[] headers = httpResponse.getAllHeaders();

            // 组装响应
            resMap.put(P.REQUEST.RES_BODY, resBody);
            resMap.put(P.REQUEST.HEADERS, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 获取请求流
     *
     * @param url
     * @param headerParams
     * @return
     */
    public static InputStream getAuthCode(String url, Map<String, Object> headerParams) {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
                .setSocketTimeout(5000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        // 拼装请求头
        if (!headerParams.isEmpty()) {
            for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Map<String, Object> logMap = new HashMap<>();
            logMap.put("请求链接", url);
            logMap.put("请求头", headerParams);
            logMap.put("请求方法", httpGet.getMethod());
            logMap.put("请求状态", statusCode);
            LogUtil.debug(LogUtil.mapToStr(logMap));

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                return entity.getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
