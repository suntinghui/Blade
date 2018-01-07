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
package org.springblade.system.service;

import org.springblade.core.annotation.DoLog;
import org.springblade.core.base.service.IService;
import org.springblade.system.model.Role;

/**
 * RoleService
 * @author zhuangqian
 */
public interface RoleService extends IService<Role> {
    /**
     * findLastNum
     * @param id
     * @return
     */
	int findLastNum(Integer id);

    /**
     * grant
     * @param ids
     * @param roleId
     * @return
     */
	@DoLog(name = "设置权限")
	boolean grant(String ids, Integer roleId);

    /**
     * getParentCnt
     * @param id
     * @return
     */
	int getParentCnt(Integer id);
}
