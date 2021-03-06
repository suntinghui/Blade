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
package org.springblade.core.toolbox.cache;

import java.util.List;

/**
 * 通用缓存接口
 * @author zhuangqian
 */
public interface ICache {
    /**
     * put
     * @param cacheName
     * @param key
     * @param value
     */
	void put(String cacheName, Object key, Object value);

    /**
     * get
     * @param cacheName
     * @param key
     * @param <T>
     * @return
     */
	<T> T get(String cacheName, Object key);

    /**
     * getKeys
     * @param cacheName
     * @return
     */
	@SuppressWarnings("rawtypes")
	List getKeys(String cacheName);

    /**
     * remove
     * @param cacheName
     * @param key
     */
	void remove(String cacheName, Object key);

    /**
     * removeAll
     * @param cacheName
     */
	void removeAll(String cacheName);

    /**
     * get
     * @param cacheName
     * @param key
     * @param iLoader
     * @param <T>
     * @return
     */
	<T> T get(String cacheName, Object key, ILoader iLoader);

    /**
     * get
     * @param cacheName
     * @param key
     * @param iLoaderClass
     * @param <T>
     * @return
     */
	<T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass);
	
}
