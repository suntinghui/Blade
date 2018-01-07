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
package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

@Table(name = "blade_role")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 角色表
 * @author zhuangqian
 */
public class Role extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 部门id
     */
	private Integer deptid;
    /**
     * 角色名
     */
	private String name;
    /**
     * 排序号
     */
	private Integer num;
    /**
     * 父角色
     */
	private Integer pid;
    /**
     *  角色别名(用于Permission注解权限检查)
     */
	private String tips;
    /**
     * 版本号
     */
	private Integer version;

	@AutoID
	@SeqID(name = "SEQ_ROLE")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
