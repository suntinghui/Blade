package org.springblade.item.makenotice.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

/**
 * @author zhuangqian
 */
@Table(name = "make_notice")
@BindID(name = "id")
// @DbName(name = "other") //多数据源配置注解
@SuppressWarnings("serial")
public class MakeNotice extends BaseModel {
    /**
     * 序列
      */
	private Integer id;
	 /**
     * 融资平台
      */
	private String platform;
    /**
     * 借款人
      */
	private String borrower;
    /**
     * 借款期限
      */
	private String borrow_date;
    /**
     * 借款金额
      */
	private Integer borrow_amount;
	 /**
     * 放款时间
      */
	private String make_time;
    /**
     * 综合利率
      */
	private float interest_rates;
	 /**
     * 借款利率
      */
	private float borrow_rates;
	 /**
     * 融资利率
      */
	private float plat_rates;
    
	/**
     * 借款用途
      */
	private String borrow_use;
    /**
     * 还款来源
      */
	private String payments_source;
    /**
     * 联系人
     */
	private String contact;
	 /**
     * 联系电话
      */
	private String phone;
    /**
     * 备注
     */
	private String remark;
	 /**
     * 合同号
     */
	private String contract_number;
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
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

	public Integer getBorrow_amount() {
		return borrow_amount;
	}

	public void setBorrow_amount(Integer borrow_amount) {
		this.borrow_amount = borrow_amount;
	}

	public String getMake_time() {
		return make_time;
	}

	public void setMake_time(String make_time) {
		this.make_time = make_time;
	}

	public float getInterest_rates() {
		return interest_rates;
	}

	public void setInterest_rates(float interest_rates) {
		this.interest_rates = interest_rates;
	}

	public float getBorrow_rates() {
		return borrow_rates;
	}

	public void setBorrow_rates(float borrow_rates) {
		this.borrow_rates = borrow_rates;
	}

	public float getPlat_rates() {
		return plat_rates;
	}

	public void setPlat_rates(float plat_rates) {
		this.plat_rates = plat_rates;
	}

	public String getBorrow_use() {
		return borrow_use;
	}

	public void setBorrow_use(String borrow_use) {
		this.borrow_use = borrow_use;
	}

	public String getPayments_source() {
		return payments_source;
	}

	public void setPayments_source(String payments_source) {
		this.payments_source = payments_source;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContract_number() {
		return contract_number;
	}

	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	
	
}
