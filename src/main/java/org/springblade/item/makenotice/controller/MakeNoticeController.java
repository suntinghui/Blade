package org.springblade.item.makenotice.controller;

import org.springblade.core.annotation.Json;
import org.springblade.core.aop.AopContext;
import org.springblade.core.meta.IQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springblade.common.base.BaseController;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.ajax.AjaxResult;
import org.springblade.core.toolbox.kit.JsonKit;
import org.springblade.item.makenotice.model.MakeNotice;
import org.springblade.item.makenotice.service.MakeNoticeService;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/makenotice")
public class MakeNoticeController extends BaseController {
	private static String CODE = "makenotice";
	private static String PREFIX = "make_notice";
	private static String LIST_SOURCE = "makenotice.list";
	private static String BASE_PATH = "/item/makenotice/";
	
	@Autowired
	MakeNoticeService service;
	
	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "makenotice.html";
	}

	@RequestMapping(KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("item_id",  getParameter("item_id"));
		mm.put("code", CODE);
		return BASE_PATH + "makenotice_add.html";
	}
	
	@RequestMapping(KEY_EDIT )
	public String edit(ModelMap mm) {
		String id = getParameter("id");
		MakeNotice notice = service.findById(id);
		mm.addAttribute("model", JsonKit.toJson(notice));
		mm.addAttribute("id", id);
		mm.addAttribute("code", CODE);
		mm.addAttribute("makenotice", notice);
		return BASE_PATH + "makenotice_edit.html";
	}

	@RequestMapping(KEY_VIEW + "/{id}")
	public String view(@PathVariable Integer id, ModelMap mm) {
		MakeNotice item = service.findById(id);
		//将javabean转化为map
		CMap cmap = CMap.parse(item);
		//使用SysCache.getDictName方法从缓存中获取对应字典项的中文值
		//将结果传回前台
		mm.put("model", JsonKit.toJson(cmap));
		mm.put("id", id);
		mm.put("code", CODE);
		mm.put("makenotice", cmap);
		return BASE_PATH + "makenotice_edit.html";
	}

	@Json
	@RequestMapping(KEY_LIST)
	public Object list() {
//		Object grid = paginate(LIST_SOURCE);
		Object grid = paginate(LIST_SOURCE, new IQuery() {
			
			public void queryBefore(AopContext ac) {
				
					String condition = " and item_id = #{item_id}";
					ac.setCondition(condition);
					ac.getParam().put("item_id", getParameter("item_id"));
			   
			}
			@Override
			public void queryAfter(AopContext ac) {
			}
			
		});
		return grid;
	}

	@Json
	@RequestMapping(KEY_SAVE)
	public AjaxResult save() {
		MakeNotice item = mapping(PREFIX, MakeNotice.class);
		item.setBorrower(getParameter("borrower"));
		int int_borrow_amount=0;
		if(!getParameter("borrow_amount").equals("") && getParameter("borrow_amount")!=null){
			int_borrow_amount = Integer.parseInt(getParameter("borrow_amount"));
		}
		item.setBorrow_amount(int_borrow_amount);
		item.setBorrow_date(getParameter("borrow_date"));
		float float_borrow_rates=0;
		if(!getParameter("borrow_rates").equals("") && getParameter("borrow_rates")!=null){
			float_borrow_rates = Float.parseFloat(getParameter("borrow_rates"));
		}
		item.setBorrow_rates(float_borrow_rates);
		item.setBorrow_use(getParameter("borrow_use"));
		item.setContact(getParameter("contact"));
		item.setContract_number(getParameter("contract_number"));
		float float_interest_rates=0;
		if(!getParameter("interest_rates").equals("") && getParameter("interest_rates")!=null){
			float_interest_rates = Float.parseFloat(getParameter("interest_rates"));
		}
		item.setInterest_rates(float_interest_rates);
		item.setPayments_source(getParameter("payments_source"));
		item.setMake_time(getParameter("make_time"));
		item.setPhone(getParameter("phone"));
		
		float float_plat_rates=0;
		if(!getParameter("plat_rates").equals("")&& getParameter("plat_rates")!=null){
			float_plat_rates = Float.parseFloat(getParameter("plat_rates"));
		}
		item.setPlat_rates(float_plat_rates);
		item.setPlatform(getParameter("platform"));
		item.setRemark(getParameter("remark"));
		item.setItem_id(getParameter("item_id"));
		boolean temp = service.save(item);
		if (temp) {
			return success(SAVE_SUCCESS_MSG);
		} else {
			return error(SAVE_FAIL_MSG);
		}
	}

	
	@Json
	@RequestMapping(KEY_UPDATE)
	public AjaxResult update() {
		MakeNotice item = mapping(PREFIX, MakeNotice.class);
		item.setId(Integer.parseInt(getParameter("id")));
		item.setBorrower(getParameter("borrower"));
		item.setBorrow_amount(Integer.parseInt(getParameter("borrow_amount")));
		item.setBorrow_date(getParameter("borrow_date"));
		item.setBorrow_rates(Float.parseFloat(getParameter("borrow_rates")));
		item.setBorrow_use(getParameter("borrow_use"));
		item.setContact(getParameter("contact"));
		item.setContract_number(getParameter("contract_number"));
		item.setInterest_rates(Float.parseFloat(getParameter("interest_rates")));
		item.setPayments_source(getParameter("payments_source"));
		item.setMake_time(getParameter("make_time"));
		item.setPhone(getParameter("phone"));
		item.setPlat_rates(Float.parseFloat(getParameter("plat_rates")));
		item.setPlatform(getParameter("platform"));
		item.setRemark(getParameter("remark"));
		boolean temp = service.update(item);
		if (temp) {
			return success(UPDATE_SUCCESS_MSG);
		} else {
			return error(UPDATE_FAIL_MSG);
		}
	}

	@Json
	@RequestMapping(KEY_REMOVE)
	public AjaxResult remove() {
		int cnt = service.deleteByIds(getParameter("ids"));
		if (cnt > 0) {
			return success(DEL_SUCCESS_MSG);
		} else {
			return error(DEL_FAIL_MSG);
		}
	}

}
