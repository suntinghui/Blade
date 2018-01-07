package org.springblade.item.item.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

/**
 * @author zhuangqian
 */
@Table(name = "item")
@BindID(name = "id")
// @DbName(name = "other") //多数据源配置注解
@SuppressWarnings("serial")
public class Item extends BaseModel {
    /**
     * 序列
      */
	private Integer id;
    /**
     * 项目名称
      */
	private String item_name;
    /**
     * 审批借款人
      */
	private String approval_borrowers;
    /**
     * 审批借款期限
      */
	private String approval_borrow_date;
    /**
     * 审批借款金额
      */
	private Integer approval_borrow_amount;
    /**
     * 审批综合利率
      */
	private float approval_interest_rates;
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


	@AutoID //mysql、postgresql自增使用
	@SeqID(name = "SEQ_NOTICE") //oracle sequence序列使用
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getApproval_borrowers() {
		return approval_borrowers;
	}

	public void setApproval_borrowers(String approval_borrowers) {
		this.approval_borrowers = approval_borrowers;
	}

	public String getApproval_borrow_date() {
		return approval_borrow_date;
	}

	public void setApproval_borrow_date(String approval_borrow_date) {
		this.approval_borrow_date = approval_borrow_date;
	}

	public Integer getApproval_borrow_amount() {
		return approval_borrow_amount;
	}

	public void setApproval_borrow_amount(Integer approval_borrow_amount) {
		this.approval_borrow_amount = approval_borrow_amount;
	}

	public float getApproval_interest_rates() {
		return approval_interest_rates;
	}

	public void setApproval_interest_rates(float approval_interest_rates) {
		this.approval_interest_rates = approval_interest_rates;
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

	
}
