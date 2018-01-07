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
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.kit.DateKit;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhuangqian
 */
public class FootTag extends Tag {

	public static String company = "北京易智友联科技有限公司";
	public static String customer = "科技有限公司";
	
	@Override
	@SuppressWarnings("unchecked")
	public void render() {
		try {
			if(args.length > 1){
				Map<String, String> param = (Map<String, String>) args[1];
			    company = (Func.isEmpty(param.get("springblade"))) ? company : param.get("springblade");
			    customer = (Func.isEmpty(param.get("customer"))) ? customer : param.get("customer");
			}
			String year = DateKit.getYear();

			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class=\"footer\">");
			sb.append("	<div class=\"footer-inner\">");
			sb.append("		<div class=\"footer-content\" style=\"height:30px;background-color:#fbfbfb;\">");
			sb.append("			<span class=\"bigger-110\">技术支持 :</span>");
			sb.append("			<span class=\"bigger-110\" id=\"support_springblade\">北京易智友联科技有限公司</span>");
			sb.append("			<span class=\"bigger-110\"  style=\"padding-left:15px;\">");
			sb.append("				© " + year);
			sb.append("			</span>");
			sb.append("		</div>");
			sb.append("	</div>");
			sb.append("</div>");
			sb.append("<a href=\"#\" id=\"btn-scroll-up\" class=\"btn-scroll-up btn btn-sm btn-inverse\">");
			sb.append(" <i class=\"ace-icon fa fa-angle-double-up icon-only bigger-110\">");
			sb.append("  顶部");
			sb.append(" </i>");
			sb.append("</a>");

			ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
