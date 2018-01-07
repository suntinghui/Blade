package org.springblade.item.item.mapper;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springblade.item.item.model.Item;
import org.springblade.modules.platform.model.Notice;

import java.util.List;

/**
 * @author zhuangqian
 */
public interface ItemMapper extends BaseMapper<Item> {

	Item findById(@Param("id") Integer id);
	
	@Sql("SELECT *  FROM item ORDER BY id DESC LIMIT 0,1")
	Item selectItemByIdDesc();
	
	@Sql("SELECT *  FROM item  WHERE  item_name=? AND  contract_number=?  ORDER BY id DESC LIMIT 0,1")
	Item selectItem(String item_name,String contract_numner);
	
}
