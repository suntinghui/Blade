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
package org.springblade.system.meta.intercept;

import org.springblade.common.tool.SysCache;
import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstCache;
import org.springblade.core.meta.MetaIntercept;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.cache.CacheKit;
import org.springblade.core.toolbox.grid.BladePage;

import java.util.List;
import java.util.Map;

/**
 * LogIntercept
 * @author zhuangqian
 */
public class LogIntercept extends MetaIntercept {
	/**
	 * 查询后操作
	 * 
	 * @param ac
	 */
	@Override
    @SuppressWarnings("unchecked")
	public void queryAfter(AopContext ac) {
		BladePage<Map<String, Object>> page = (BladePage<Map<String, Object>>) ac.getObject();
		List<Map<String, Object>> list = page.getRows();
		for (Map<String, Object> map : list) {
			String succeedName = (Func.toInt(map.get("succeed"), 1) == 1) ? "成功" : "失败";
			map.put("succeedname", succeedName);
			map.put("username", SysCache.getUserName(map.get("userid")));
		}
	}

	/**
	 * 查看转向前操作
	 * 
	 * @param ac
	 */
	@Override
    public void renderViewBefore(AopContext ac) {
		CMap cmap = (CMap) ac.getObject();
		String succeedName = (cmap.getInt("succeed") == 1) ? "成功" : "失败";
		cmap.set("succeedName", succeedName).set("userName", SysCache.getUserName(cmap.get("userid")));
	}
	
	
	
	/**
	 * 主表新增后操作(事务内)
	 * 
	 * @param ac
	 */
	@Override
    public boolean saveAfter(AopContext ac) {
		CacheKit.removeAll(ConstCache.SYS_CACHE);
		return true;
	}
	
	/**
	 * 主表修改后操作(事务内)
	 * 
	 * @param ac
	 */
	@Override
    public boolean updateAfter(AopContext ac) {
		CacheKit.removeAll(ConstCache.SYS_CACHE);
		return true;
	}

	
	/**
	 * 物理删除后操作(事务内)
	 * 
	 * @param ac
	 */
	@Override
    public boolean removeAfter(AopContext ac) {
		CacheKit.removeAll(ConstCache.SYS_CACHE);
		return true;
	}
	
}
