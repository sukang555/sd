package com.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author sukang
 */
public class MyShiroRealm extends AuthorizingRealm{


    private static final String USER_NAME = "sukang";

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken loginUser = (UsernamePasswordToken) authenticationToken;
        String username = String.valueOf(loginUser.getPrincipal());

        if (!USER_NAME.equals(username)){
            throw new UnknownAccountException("未找到用户名");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                USER_NAME,
                //密码
                "123456",
                //salt
                ByteSource.Util.bytes("123"),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
