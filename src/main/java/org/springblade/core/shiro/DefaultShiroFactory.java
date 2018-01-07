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

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springblade.common.vo.ShiroUser;
import org.springblade.core.constant.ConstCache;
import org.springblade.core.constant.ConstCacheKey;
import org.springblade.core.plugins.dao.Blade;
import org.springblade.core.plugins.dao.Db;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.support.Convert;
import org.springblade.system.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Shiro默认工厂类
 * @author zhuangqian
 */
public class DefaultShiroFactory implements IShiro{
	
	@Override
    public User user(String account) {
		User user = Blade.create(User.class).findFirstBy("account = #{account}", CMap.init().set("account", account));
		// 账号不存在
		if (null == user) {
			throw new UnknownAccountException();
		}
		// 账号未审核
        boolean notAuth = user.getStatus() == 3 || user.getStatus() == 4;
		if (notAuth) {
			throw new DisabledAccountException();
		}
		// 账号被冻结
        boolean frozen = user.getStatus() == 2 || user.getStatus() == 5;
		if (frozen) {
			throw new DisabledAccountException();
		}
		return user;
	}

	@Override
    public ShiroUser shiroUser(User user) {
		List<Integer> roleList = new ArrayList<>();
		Integer[] roles = Convert.toIntArray(user.getRoleid());
		for (int i = 0; i < roles.length; i++) {
			roleList.add(roles[i]);
		}
		return new ShiroUser(user.getId(), user.getDeptid(), user.getAccount(), user.getName(), roleList);
	}

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> findPermissionsByRoleId(final Object userId, Integer roleId) {
		Map<String, Object> userRole =  Db.selectOneByCache(ConstCache.SYS_CACHE,
															ConstCacheKey.ROLE_EXT + userId,
															"select * from BLADE_ROLE_EXT where USERID=#{userId}", 
															CMap.init().set("userId", Convert.toInt(userId)));

		String roleIn = "0";
		String roleOut = "0";
		if (!Func.isEmpty(userRole)) {
			CMap cmap = CMap.parse(userRole);
			roleIn = cmap.getStr("ROLEIN");
			roleOut = cmap.getStr("ROLEOUT");
		}
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("select ID,CODE,URL from BLADE_MENU  ");
		sql.append(" where ( ");
		sql.append("	 (status=1)");
		sql.append("	 and (url is not null) ");
		sql.append("	 and (id in (select menuId from BLADE_RELATION where roleId = #{roleId}) or id in (#{join(roleIn)}))");
		sql.append("	 and id not in(#{join(roleOut)})");
		sql.append("	)");
		sql.append(" order by levels,pCode,num");

		List<Map> permissions = Db.selectListByCache(ConstCache.SYS_CACHE, ConstCacheKey.PERMISSIONS + userId, sql.toString(), CMap.init()
				.set("roleId", roleId).set("roleIn", Convert.toIntArray(roleIn)).set("roleOut", Convert.toIntArray(roleOut)));
		
		return permissions;
	}

	@Override
    @SuppressWarnings("unchecked")
	public String findRoleNameByRoleId(final Integer roleId) {
		Map<String, Object> map = Db.selectOneByCache(ConstCache.SYS_CACHE, 
														ConstCacheKey.GET_ROLE_NAME_BY_ID + roleId, 
														"select TIPS from BLADE_ROLE where id = #{id}", 
														CMap.init().set("id", roleId));
		return Func.toStr(map.get("TIPS"));
	}

	@Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
		String credentials = user.getPassword();
		// 密码加盐处理
		String source = user.getSalt();
		ByteSource credentialsSalt = new Md5Hash(source);
		return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
	}

}
