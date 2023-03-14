package com.example.nacostest.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 *
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 19:27
 */
public class MyRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取获取身份信息
        String principal = authenticationToken.getPrincipal().toString();
        //2获取凭证信息
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println("认证的用户信息"+principal+"---"+password);
        //3获取数据库中存储的用户信息
        if ("zhangsan".equals(principal)){
            String pwdInfo = "7174f64b130...";
            //4创建封装校验逻辑的对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    pwdInfo,
                    ByteSource.Util.bytes("salt"),
                    principal
            );
            return info;
        }

        return null;
    }
}
