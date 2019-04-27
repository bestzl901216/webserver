package com.ricardo.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Ricardo
 * @date 2019/4/27
 */
@Controller
@RequestMapping("weChatViews")
public class WeChatViewsController {

    @RequestMapping("hello")
    public String hello(Map<String, Object> map) {
        map.put("msg", "你好帅哥1234");
        return "hello";
    }
}
