package org.springblade.item.itemother.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springblade.common.base.BaseController;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/itemother")
public class ItemOtherController extends BaseController {
	private static String BASE_PATH = "/item/itemother/";

	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		return BASE_PATH + "itemother.html";
	}
}
