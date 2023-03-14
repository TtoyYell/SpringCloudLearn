package com.example.nacostest.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 20:44
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RealmBean realmBean;

    // 配置SecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager (){
        // 1创建defaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 2创建加密对象，设置相关属性
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 2.1采用md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 2.2采用迭代加密次数
        hashedCredentialsMatcher.setHashIterations(3);
        // 3将加密对象存储到realm当中
        realmBean.setCredentialsMatcher(hashedCredentialsMatcher);
        // 4将reamlBean存入defaultWebSecurityManager对象中
        defaultWebSecurityManager.setRealm(realmBean);

        // 5添加rememberme
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }

    // 创建shiro的cookie管理对象
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    // cookie属性设置
    private SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 设置跨域
        // simpleCookie.setDomain(domain);
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(30*24*60*60);
        return simpleCookie;
    }

    // 配置shiro内置过滤器的拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition =
                new DefaultShiroFilterChainDefinition();
        // 设置不认证可以访问的资源
        definition.addPathDefinition("/test3/login","anon");
        // 设置登出过滤器
        definition.addPathDefinition("/logout","logout");
        // 设置需要登录认证的拦截范围
        definition.addPathDefinition("/**","authc");
        definition.addPathDefinition("/**","user");
        return definition;
    }

}
