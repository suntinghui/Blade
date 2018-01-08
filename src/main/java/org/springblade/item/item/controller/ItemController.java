package org.springblade.item.item.controller;

import org.springblade.common.tool.SysCache;
import org.springblade.core.annotation.Json;
import org.springblade.core.plugins.dao.Md;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springblade.common.base.BaseController;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.ajax.AjaxResult;
import org.springblade.core.toolbox.kit.JsonKit;
import org.springblade.item.item.mapper.ItemMapper;
import org.springblade.item.item.model.Item;
import org.springblade.item.item.service.ItemService;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
	private static String CODE = "item";
	private static String PREFIX = "item";
	private static String LIST_SOURCE = "item.list";
	private static String BASE_PATH = "/item/item/";

	@Autowired
	ItemService service;

	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "item.html";
	}

	@RequestMapping(KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "item_add.html";
	}

	@RequestMapping(KEY_EDIT)
	public String edit(ModelMap mm) {
		String id = getParameter("id");
		Item notice = service.findById(id);
		mm.put("model", JsonKit.toJson(notice));
		mm.put("id", id);
		mm.put("code", CODE);
		mm.put("item", notice);
		return BASE_PATH + "item_edit.html";
	}

	@RequestMapping(KEY_VIEW + "/{id}")
	public String view(@PathVariable Integer id, ModelMap mm) {
		Item item = service.findById(id);
		// 将javabean转化为map
		CMap cmap = CMap.parse(item);
		// 使用SysCache.getDictName方法从缓存中获取对应字典项的中文值
		// cmap.set("typename", SysCache.getDictName(102, item.getType()));
		// 将结果传回前台
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
		Item item = mapping(PREFIX, Item.class);
		int temp = service.saveRtId(item);
		if (temp > 0) {
			item.setId(temp);
			return successItem(SAVE_SUCCESS_MSG, item);
		} else {
			return error(SAVE_FAIL_MSG);
		}
	}

	@Json
	@RequestMapping(KEY_UPDATE)
	public AjaxResult update() {
		Item item = mapping(PREFIX, Item.class);
		boolean temp = service.update(item);
		if (temp) {
			return successItem(UPDATE_SUCCESS_MSG, item);
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
