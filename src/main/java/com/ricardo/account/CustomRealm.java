package com.ricardo.shiro;

import com.ricardo.shiro.mapper.AccountMapper;
import com.ricardo.shiro.mapper.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Component
@Slf4j
public class CustomRealm extends AuthorizingRealm implements InitializingBean {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("enter method doGetAuthorizationInfo");

        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        Account account = (Account) getAvailablePrincipal(principalCollection);
        log.debug("account = [{}]", account);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(account.getRoles());
        info.setStringPermissions(account.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("enter method doGetAuthenticationInfo");

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String name = upToken.getUsername();
        if (name == null) {
            throw new AccountException("Null name are not allowed by this realm.");
        }

        Account account = accountMapper.findAccountByName(name);
        log.debug("account = [{}]", account);
        if (account == null) {
            throw new UnknownAccountException("No account found for admin [" + name + "]");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, account.getPwd(), getName());
        if (account.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(account.getSalt()));
        }

        return info;
    }

    @Override
    public void afterPropertiesSet() {
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashMatcher);
    }
}
