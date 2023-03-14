package com.example.nacostest.config;

import com.example.nacostest.entity.User;
import com.example.nacostest.service.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 20:37
 */
@Component
public class RealmBean extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    // 自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取获取身份信息
        String principal = authenticationToken.getPrincipal().toString();
        //2获取凭证信息
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println("认证的用户信息: "+principal+"---"+password);
        //3获取数据库中存储的用户信息
        User user = userService.getUserInfoByName(principal);
        if (user != null){
            //4创建封装校验逻辑的对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    principal
            );
            return info;
        }
        return null;
    }
}
