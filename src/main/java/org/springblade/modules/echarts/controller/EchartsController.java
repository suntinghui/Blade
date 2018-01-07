package org.springblade.modules.echarts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springblade.common.base.BaseController;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController extends BaseController {

	@GetMapping
	public String echarts() {
		return "/modules/echarts/echarts.html";
	}
	
}
