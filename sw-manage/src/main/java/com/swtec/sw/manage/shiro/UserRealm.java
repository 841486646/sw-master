package com.swtec.sw.manage.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.swtec.sw.persist.model.User;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

/**
 * 用于获取用户权限数据
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserRealm(ApplicationContext ctx) {
        super();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findByUserName(userName);
        if(user != null) {
        	authorizationInfo.setRoles(userService.findStringRoles(user));
            authorizationInfo.setStringPermissions(userService.findStringPermissions(user));
        }
        
        return authorizationInfo;
    }

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try {
        	user = userService.login(username, password);
        } catch (DbException e) {
        	log.error("数据库操作失败", e);
            throw new AuthenticationException(e.getMessage());
        } catch (BizException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (Exception e) {
            log.error("login error", e);
            throw new AuthenticationException(e.getMessage());
        }
        if(user == null){
        	throw new AuthenticationException(RespCode.NO_USERINFO.getMsg());
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginName(), password.toCharArray(), getName());
        return info;
    }

}
