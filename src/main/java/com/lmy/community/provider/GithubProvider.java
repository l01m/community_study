package com.lmy.community.provider;

import com.alibaba.fastjson.JSON;
import com.lmy.community.dto.AccessTokenDTO;
import com.lmy.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


//把普通pojo实例化到spring容器中（pojo代表普通JavaBeans）
@Component
public class GithubProvider {
    /**
     * OkHttp模拟post请求
     *调用地址传递参数获取access_token
     *
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                //url地址为github官方文档提供地址
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //携带code调用github的access_token API最终获得Access_Token
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * OkHttp模拟get请求获取用户名
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //将获取到的json内容解析为GithubUser对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}