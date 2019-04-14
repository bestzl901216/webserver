package com.ricardo.wechat;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.model.menu.ClickButton;
import org.weixin4j.model.menu.Menu;
import org.weixin4j.model.menu.SingleButton;
import org.weixin4j.model.menu.ViewButton;
import org.weixin4j.spring.WeixinTemplate;
import org.weixin4j.spring.web.WeixinJieruController;

import java.util.List;

/**
 * @author Ricardo
 * @date 2019/4/14
 */
@Api(tags = "微信对接资源接口")
@Slf4j
@RestController
@RequestMapping("weChat")
public class WeChatAccessController extends WeixinJieruController {

    private WeixinTemplate weixinTemplate;

    @Autowired
    private WeChatAccessController(WeixinTemplate weixinTemplate) {
        this.weixinTemplate = weixinTemplate;
    }

    @ApiOperation("获取appId")
    @GetMapping("appId")
    public String getAppId() {
        return weixinTemplate.getAppId();
    }

    @ApiOperation("创建公众号菜单")
    @PostMapping("menu")
    public void createMenu() {
        //3.1获取菜单组件
        MenuComponent menuComponent = weixinTemplate.menu();
        //调用组件创建方法
        Menu menu = new Menu();
        List<SingleButton> buttons = Lists.newArrayList();
        //创建菜单1 click button
        ClickButton button1 = new ClickButton("今日歌曲", "V1001_TODAY_MUSIC");
        //添加一级菜单1
        buttons.add(button1);
        //创建菜单2 可以包含二级子菜单
        SingleButton button2 = new SingleButton("菜单");
        //创建2级子菜单1，打开页面
        ViewButton button2sub1 = new ViewButton("搜索", "http://www.soso.com/");
        button2.getSubButton().add(button2sub1);
        //创建2级子菜单3，点击事件
        ClickButton button2sub3 = new ClickButton("赞一下我们", "V1001_GOOD");
        button2.getSubButton().add(button2sub3);
        //添加一级菜单2
        buttons.add(button2);
        //设置自定义菜单
        menu.setButton(buttons);
        try {
            //调用微信自定义菜单组件，创建自定义菜单
            menuComponent.create(menu);
        } catch (WeixinException e) {
            //打印创建异常日志
            e.printStackTrace();
        }
    }
}
