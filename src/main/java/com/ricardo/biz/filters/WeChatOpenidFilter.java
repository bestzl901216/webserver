package com.ricardo.biz.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.WeixinException;
import org.weixin4j.spring.WeixinTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 访问公众号页面时，自动获取用户openid
 *
 * @author Ricardo
 * @date 2019/4/27
 */
@Slf4j
@Component
public class WeChatOpenidFilter implements Filter {

    private static final String PREFIX = "/weChatViews/";
    private static final String SESSION_KEY_OPENID = "weChat_openid";
    private static final String SESSION_KEY_CODE = "weChat_code";

    private WeixinTemplate weixinTemplate;

    @Autowired
    private WeChatOpenidFilter(WeixinTemplate weixinTemplate) {
        this.weixinTemplate = weixinTemplate;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String targetUrl = httpServletRequest.getRequestURI();
        log.info("请求路径：{}", targetUrl);
        if (!targetUrl.startsWith(PREFIX)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = httpServletRequest.getSession();
        String openid = this.getStringValueFromSession(session, SESSION_KEY_OPENID);
        if (openid == null) {
            String code = this.getStringValueFromSession(session, SESSION_KEY_CODE);
            if (code == null) {
                String codeBaseUrl = weixinTemplate.sns().getOAuth2CodeBaseUrl(targetUrl);
                log.info("codeBaseUrl：{}", codeBaseUrl);
                httpServletResponse.sendRedirect(codeBaseUrl);
                this.setStringValueToSession(session, SESSION_KEY_CODE, null);
                return;
            }
            code = httpServletRequest.getParameter("code");
            log.info("code：{}", code);
            if (code != null) {
                try {
                    openid = weixinTemplate.sns().getOpenId(code);
                    this.setStringValueToSession(session, SESSION_KEY_OPENID, openid);
                } catch (WeixinException e) {
                    log.warn("通过code换取网页授权access_token失败");
                    this.setStringValueToSession(session, SESSION_KEY_OPENID, null);
                }
            } else {
                log.warn("请求参数中没有code");
                this.setStringValueToSession(session, SESSION_KEY_OPENID, null);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getStringValueFromSession(HttpSession session, String key) {
        Object obj = session.getAttribute(key);
        return obj instanceof String ? (String) obj : null;
    }

    private void setStringValueToSession(HttpSession session, String key, String value) {
        session.setAttribute(key, value == null ? "" : value);
    }
}
