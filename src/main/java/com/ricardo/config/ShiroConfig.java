package com.ricardo.config;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // 静态资源匿名访问
        chainDefinition.addPathDefinition("/static/**", "anon");
        // 监控接口匿名访问
        chainDefinition.addPathDefinition("/actuator/**", "anon");
        // 账号登录接口匿名访问
        chainDefinition.addPathDefinition("/account/login", "anon");
        // swagger-ui匿名访问
        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");
        chainDefinition.addPathDefinition("/v2/**", "anon");
        chainDefinition.addPathDefinition("/webjars/**", "anon");
        // 微信对接接口匿名访问
        chainDefinition.addPathDefinition("/weChat/**", "anon");
        // 其他资源均需授权访问
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /*
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     */
    /*@Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }*/

}
