package org.springblade.item.itemfile.model;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

/**
 * @author zhuangqian
 */
@Table(name = "item_file")
@BindID(name = "id")
// @DbName(name = "other") //多数据源配置注解
@SuppressWarnings("serial")
public class ItemFile extends BaseModel {
    /**
     * 序列
      */
	private Integer id;
    /**
     * 文件名称
      */
	private String name;
    /**
     * 上传人
      */
	private String creater;
    /**
     * 上传时间
      */
	private Date createtime;
	/**
     * 上传项目文件类型
      */
	private String type;
	/**
     * 上传项目文件路径
      */
	private String url;
	/**
     * 所属项目
      */
	private String item_id;
   
	

	

	@AutoID //mysql、postgresql自增使用
	@SeqID(name = "SEQ_NOTICE") //oracle sequence序列使用
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

}
