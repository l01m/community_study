package com.lmy.community.controller;

import com.lmy.community.dto.AccessTokenDTO;
import com.lmy.community.dto.GithubUser;
import com.lmy.community.mapper.UserMapper;
import com.lmy.community.model.User;
import com.lmy.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    //HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，HTTP请求头中的所有信息都封装在这个对象中，通过这个对象提供的方法，可以获得客户端请求的所有信息
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //传递Access_Token参数
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //解析Access_Token中的内容获取用户名
        GithubUser githubProviderUser = githubProvider.getUser(accessToken);
        if(githubProviderUser != null){
            //将获取到的值传递到User中
            User user = new User();
            //自动随机生成id
            user.setId(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubProviderUser.getId()));
            user.setName(githubProviderUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登录成功，写入 Seesion 和Cookies
            request.getSession().setAttribute("user", user);
            //redirect 重定向到根目录，加载默认页面
            return "redirect:/";
        }else{
            //登录不成功
            return "redirect:/";
        }
    }
}
