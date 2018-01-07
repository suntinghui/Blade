package org.springblade.system.controller;

import org.springblade.common.base.BaseController;
import org.springblade.core.constant.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * MainController
 * @author zhuangqian
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

	@GetMapping
	public String index() {
		return Const.INDEX_MAIN_REALPATH;
	}
	
}
