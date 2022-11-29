package com.qq.group.infrastructure.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * @author yanzhonglong
 * @date 2020/12/24 17:05
 */
public class SendHttpUtil {

    /**
     * 发送get请求
     *
     * @param url
     * @return json
     * @throws IOException
     */
    public static JSONObject sendGetHttp(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONArray sendGetHttpArray(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return JSONArray.parseArray(response.body().string());
    }

    /**
     * 发送post请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject sendPostHttp(String url, String param) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return JSONObject.parseObject(response.body().string());
    }

    /**
     * 发送post请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPostHttpRequestXml(String url, String param) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/xml")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
