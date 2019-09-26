package com.lmy.community.controller;

import com.lmy.community.mapper.UserMapper;
import com.lmy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 25711
 */
@Controller
public class IndexController {


    //@autowired注解它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过注解的使用来消除 set ，get方法。
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")//单独一个/代表根目录
    public String index(HttpServletRequest request){
        //获取cookies，判断是否已token中的值一致
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }

}
