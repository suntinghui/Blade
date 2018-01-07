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
package org.springblade.core.beetl.tag;

import org.beetl.core.Tag;
import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstCache;
import org.springblade.core.constant.ConstCacheKey;
import org.springblade.core.constant.Cst;
import org.springblade.core.meta.IQuery;
import org.springblade.core.plugins.dao.Db;
import org.springblade.core.plugins.dao.Md;
import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.cache.CacheKit;
import org.springblade.core.toolbox.cache.ILoader;
import org.springblade.core.toolbox.kit.ClassKit;
import org.springblade.core.toolbox.kit.JsonKit;
import org.springblade.core.toolbox.kit.StrKit;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhuangqian
 */
public class SelectTag extends Tag {

	@Override
	@SuppressWarnings("unchecked")
	public void render() {
		try {
			Map<String, String> param = (Map<String, String>) args[1];

			final String code = param.get("code");
			String name = param.get("name");
			String value = Func.toStr(param.get("value"));
			String token = (StrKit.notBlank(value)) ? "" : Func.toStr(param.get("token"));
			String type = param.get("type");
			String where = param.get("where");
			String required = param.get("required");
			String tail = param.get("tail");
			String inter = param.get("intercept");
			String sql = "";
			
			Map<String, Object> modelOrMap = CMap.createHashMap();
			
			IQuery intercept = Cst.me().getDefaultQueryFactory();
			
			String CACHE_NAME = ConstCache.SYS_CACHE;

            String DICT = "dict";
            String USER = "user";
            String DEPT = "dept";
            String ROLE = "role";
            String DIY = "diy";
			if (type.equals(DICT)) {
				sql = "select num as ID,pId as PID,name as TEXT from  BLADE_DICT where code='" + code + "' and num > 0 order by num asc";
				intercept = Cst.me().getDefaultSelectFactory().dictIntercept();
			} else if (type.equals(USER)) {
				CACHE_NAME = ConstCache.SYS_CACHE;
				sql = "select ID,name as TEXT from  BLADE_USER where status=1";
				intercept = Cst.me().getDefaultSelectFactory().userIntercept();
			} else if (type.equals(DEPT)) {
				CACHE_NAME = ConstCache.SYS_CACHE;
				sql = "select ID,PID,SIMPLENAME as TEXT from  BLADE_DEPT";
				intercept = Cst.me().getDefaultSelectFactory().deptIntercept();
			} else if (type.equals(ROLE)) {
				CACHE_NAME = ConstCache.SYS_CACHE;
				sql = "select ID,name as TEXT from  BLADE_ROLE";
				intercept = Cst.me().getDefaultSelectFactory().roleIntercept();
			} else if (type.equals(DIY)) {
				CACHE_NAME = ConstCache.SYS_CACHE;
				type = type + "_" + param.get("source");
				if(StrKit.notBlank(where)){
					modelOrMap = JsonKit.parse(where, Map.class);
				}
				sql = Md.getSql(param.get("source"));
			}

			if(StrKit.notBlank(inter)) {
				intercept = ClassKit.newInstance(inter);
			}
			
			final String _sql = sql;
			final Map<String, Object> _modelOrMap = modelOrMap;
			final IQuery _intercept = intercept;
			
			List<Map<String, Object>> dict = CacheKit.get(CACHE_NAME, ConstCacheKey.DICT + type + "_" + code + "_" + ShiroKit.getUser().getId(), new ILoader() {
				@Override
				public Object load() {
					return Db.selectList(_sql, _modelOrMap, new AopContext(), _intercept);
				}
			}); 

			StringBuilder sb = new StringBuilder();
			String [] arr = name.split("\\.");
			String sid = arr[0];
			if (arr.length == 2) {
				sid = arr[1];
			}
			sid = "_" + sid;
			sb.append("<select onchange=\"" +sid + "_selectChanged('" + sid + "')\" " + required + " class=\"form-control\" id=\"" + sid + "\"  name=\"" + token + name + "\">");
			sb.append("<option value></option>");
			
			for (Map<String, Object> dic : dict) {
				String id =  Func.toStr(dic.get("ID"));
				String selected = "";
				if (Func.equals(id, value)) {
					selected = "selected";
				}
				sb.append("<option " + selected + " value=\"" + id + "\">" + dic.get("TEXT") + "</option>");
			}
			sb.append("</select>");
			
			
			sb.append("<script type=\"text/javascript\">");
			sb.append("		function " +sid + "_selectChanged(sid) {");
			sb.append("			$('#form_token').val(1);");
			sb.append("			$('#' + sid).attr('name','"+name+"');");
			if(StrKit.notBlank(tail)) {
				sb.append("			var options=$('#' + sid + ' option:selected');");
				sb.append("			$('#' + sid + '_ext').val(options.text());");
			}
			sb.append("		};");
			sb.append("</script>");
			if(StrKit.notBlank(tail)) {
				sb.append("<input type=\"hidden\" id=\"" + sid + "_ext\" name=\"" + name.split("\\.")[0] + "." + tail + "\">");
			}

			ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
