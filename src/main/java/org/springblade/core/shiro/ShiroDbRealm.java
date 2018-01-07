/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.core.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springblade.common.vo.ShiroUser;
import org.springblade.core.toolbox.Func;
import org.springblade.system.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ShiroDbRealm
 * @author zhuangqian
 */
public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger log = LogManager.getLogger(ShiroDbRealm.class);
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		log.info("Shiro登录认证启动");
		
		IShiro shiroFactory = ShiroManager.me().getDefaultShiroFactory();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		User user = shiroFactory.user(token.getUsername());

		ShiroUser shiroUser = shiroFactory.shiroUser(user);

		SimpleAuthenticationInfo info = shiroFactory.info(shiroUser, user, getName());

		log.info("Shiro登录认证完毕");
		return info;
	}

	/**
	 * 权限认证
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		IShiro shiroFactory = ShiroManager.me().getDefaultShiroFactory();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		Object userId = shiroUser.getId();
		List<Integer> roleList = shiroUser.getRoleList();
		Set<String> urlSet = new HashSet<>();
		Set<String> roleNameSet = new HashSet<>();
		for (Integer roleId : roleList) {
			List<Map> permissions = shiroFactory.findPermissionsByRoleId(userId, roleId);
			if (null != permissions) {
				for (Map map : permissions) {
					if (!Func.isEmpty(map.get("URL"))) {
						urlSet.add(Func.toStr(map.get("URL")));
					}
				}
			}
			String roleName = shiroFactory.findRoleNameByRoleId(roleId);
			roleNameSet.add(roleName);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		info.addRoles(roleNameSet);
		return info;
	}

	/**
	 * 设置认证加密方式
	 */
	@PostConstruct
	public void setCredentialMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
		credentialsMatcher.setHashIterations(ShiroKit.hashIterations);
		setCredentialsMatcher(credentialsMatcher);
	}

}