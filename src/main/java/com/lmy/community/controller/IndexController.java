package com.lmy.community.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
/**
 * Created by 25711
 */
@Controller
public class IndexController {
    @GetMapping("/")//单独一个/代表根目录
    public String index(){ return "index"; }

}
