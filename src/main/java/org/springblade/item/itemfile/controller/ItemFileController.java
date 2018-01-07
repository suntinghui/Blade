package org.springblade.item.itemfile.controller;

import org.springblade.core.annotation.Json;
import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstConfig;
import org.springblade.core.meta.IMeta;
import org.springblade.core.meta.IQuery;
import org.springblade.core.plugins.dao.Db;
import org.springblade.core.shiro.ShiroKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.springblade.common.base.BaseController;
import org.springblade.common.tool.SysCache;
import org.springblade.core.toolbox.ajax.AjaxResult;
import org.springblade.core.toolbox.grid.BladePage;
import org.springblade.item.item.model.Item;
import org.springblade.item.item.service.ItemService;
import org.springblade.item.itemfile.model.ItemFile;
import org.springblade.item.itemfile.service.ItemFileService;
import org.springblade.system.meta.factory.ItemFileFactory;

/**
 * @author zhuangqian
 */
@Controller
@RequestMapping("/itemfile")
public class ItemFileController extends BaseController {
	private static String CODE = "itemfile";
	private static String PREFIX = "item_file";
	private static String LIST_SOURCE = "itemfile.list";
	private static String BASE_PATH = "/item/itemfile/";
	
	@Autowired
	ItemFileService service;
	
	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "itemfile.html";
	}

	@RequestMapping(KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("code", CODE);
		Session session = ShiroKit.getSession();
		session.setAttribute("type", getParameter("type"));
		session.setAttribute("item_id", getParameter("item_id"));
		return BASE_PATH + "itemfile_add.html";
	}

	protected Class<? extends IMeta> metaFactoryClass() {
		
		return ItemFileFactory.class;
	}

	@Json
	@RequestMapping(KEY_LIST)
	public Object list() {
		Object grid = paginate(LIST_SOURCE, new IQuery() {

			public void queryBefore(AopContext ac) {
					//String condition = " and task_code = #{task_code}";
					//ac.setCondition(condition);
					//ac.getParam().put("task_code", getParameter("task_code"));
			   
			}
			@Override
			public void queryAfter(AopContext ac) {
				BladePage<Map<String, Object>> page = (BladePage<Map<String, Object>>) ac.getObject();
				List<Map<String, Object>> list = page.getRows();
				for (Map<String, Object> map : list) {
					map.put("creatername", SysCache.getUserName(map.get("creater")));
				}
				
			}
			
		});
		
		return grid;
	}

	@Json
	@RequestMapping(KEY_SAVE)
	public AjaxResult save() {
		ItemFile item = mapping(PREFIX, ItemFile.class);
		boolean temp = service.save(item);
		if (temp) {
			return success(SAVE_SUCCESS_MSG);
		} else {
			return error(SAVE_FAIL_MSG);
		}
	}

	

	@Json
	@RequestMapping(KEY_DETELE)
	public AjaxResult detele() {
		int cnt = service.deleteByIds(getParameter("ids"));
		if (cnt > 0) {
			return success(DEL_SUCCESS_MSG);
		} else {
			return error(DEL_FAIL_MSG);
		}
	}
	
	@Json
	@RequestMapping(KEY_QUERY_ITEM_FILE)
	public AjaxResult queryItemFile() {
		String type = getParameter("type");
		String item_id = getParameter("item_id");
		String where  = "" ;
		String sql="select a.*,u.name as creatername from  item_file a left join blade_user u on a.creater=u.id where 1=1 ";
		
		if(!type.isEmpty() && type != "" && type !=null ){
			if(!type.equals("1")){
				where = " and type='"+type+"' ";
			}
		}
		if(!item_id.isEmpty() && item_id!="" ){
			where += " and item_id='"+item_id+"'";
		}
		sql+=where;
		List<Map> list=Db.selectList(sql);
		// Map map= new HashMap();
		
		List<Map> list1 =new ArrayList();
		for (Map map : list) {
			map.put("itemfileurl", ConstConfig.DOMAIN + map.get("url"));
			list1.add(map);
		}
		
		Map map1= new HashMap();
	   
	    map1.put("date1", list1);
	   // map.put("itemfileurl", ConstConfig.DOMAIN);
	    Session session = ShiroKit.getSession();
	    if(session.getAttribute("type")!=null && !session.getAttribute("type").toString().trim().equals("")){
	    	session.removeAttribute("type");
	    }
	    if(session.getAttribute("item_id")!=null && !session.getAttribute("item_id").toString().trim().equals("")){
	    	session.removeAttribute("item_id");
	    }
		return json(map1);
	}

}
