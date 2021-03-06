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

@Table(name = "blade_parameter")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 参数表
 * @author zhuangqian
 */
public class Parameter extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 参数编号
     */
	private String code;
    /**
     * 参数名
     */
	private String name;
    /**
     * 排序号
     */
	private Integer num;
    /**
     * 参数值
     */
	private String para;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 备注
     */
	private String tips;
    /**
     * 版本号
     */
	private Integer version;

	@AutoID
	@SeqID(name = "SEQ_PARAMETER")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
