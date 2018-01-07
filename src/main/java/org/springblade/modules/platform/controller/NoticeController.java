package org.springblade.modules.platform.controller;

import org.springblade.common.tool.SysCache;
import org.springblade.core.annotation.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springblade.common.base.BaseController;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.ajax.AjaxResult;
import org.springblade.core.toolbox.kit.JsonKit;
import org.springblade.modules.platform.model.Notice;
import org.springblade.modules.platform.service.NoticeService;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	private static String CODE = "notice";
	private static String PREFIX = "blade_notice";
	private static String LIST_SOURCE = "notice.list";
	private static String BASE_PATH = "/modules/platform/notice/";
	
	@Autowired
	NoticeService service;
	
	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "notice.html";
	}

	@RequestMapping(KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "notice_add.html";
	}

	@RequestMapping(KEY_EDIT + "/{id}")
	public String edit(@PathVariable Integer id, ModelMap mm) {
		Notice notice = service.findById(id);
		mm.put("model", JsonKit.toJson(notice));
		mm.put("id", id);
		mm.put("code", CODE);
		return BASE_PATH + "notice_edit.html";
	}

	@RequestMapping(KEY_VIEW + "/{id}")
	public String view(@PathVariable Integer id, ModelMap mm) {
		Notice notice = service.findById(id);
		//将javabean转化为map
		CMap cmap = CMap.parse(notice);
		//使用SysCache.getDictName方法从缓存中获取对应字典项的中文值
		cmap.set("typename", SysCache.getDictName(102, notice.getType()));
		//将结果传回前台
		mm.put("model", JsonKit.toJson(cmap));
		mm.put("id", id);
		mm.put("code", CODE);
		return BASE_PATH + "notice_view.html";
	}

	@Json
	@RequestMapping(KEY_LIST)
	public Object list() {
		Object grid = paginate(LIST_SOURCE);
		return grid;
	}

	@Json
	@RequestMapping(KEY_SAVE)
	public AjaxResult save() {
		Notice notice = mapping(PREFIX, Notice.class);
		boolean temp = service.save(notice);
		if (temp) {
			return success(SAVE_SUCCESS_MSG);
		} else {
			return error(SAVE_FAIL_MSG);
		}
	}

	@Json
	@RequestMapping(KEY_UPDATE)
	public AjaxResult update() {
		Notice notice = mapping(PREFIX, Notice.class);
		boolean temp = service.update(notice);
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
