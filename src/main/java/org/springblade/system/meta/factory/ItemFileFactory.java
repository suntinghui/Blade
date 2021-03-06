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
package org.springblade.system.meta.factory;

import org.springblade.core.meta.MetaIntercept;
import org.springblade.core.meta.MetaManager;
import org.springblade.item.itemfile.model.ItemFile;
import org.springblade.system.meta.intercept.AttachIntercept;
import org.springblade.system.meta.intercept.ItemFileIntercept;
import org.springblade.system.model.Attach;

import java.util.HashMap;
import java.util.Map;

/**
 * AttachFactory
 * @author zhuangqian
 */
public class ItemFileFactory extends MetaManager {

	@Override
    public Class<? extends MetaIntercept> intercept() {
		return ItemFileIntercept.class;
	}
	
	@Override
    public String controllerKey() {
		return "itemfile";
	}

	@Override
    public String paraPrefix() {
		return getTableName(ItemFile.class);
	}

	@Override
    public Map<String, String> renderMap() {
		Map<String, String> renderMap = new HashMap<>();
		renderMap.put(KEY_INDEX, "/item/itemfile/itemfile.html");
		renderMap.put(KEY_ADD, "/item/itemfile/itemfile_add.html"); 
		return renderMap;
	}

	@Override
    public Map<String, String> sourceMap() {
		Map<String, String> sourceMap = new HashMap<>();
		sourceMap.put(KEY_INDEX, "itemfile.sourceList");
		return sourceMap;
	}

}
