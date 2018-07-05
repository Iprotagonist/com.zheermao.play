package com.taotao.mapper;

import com.taotao.pojo.Tbitemdesc;

public interface TbitemdescMapper {
	/**
	 * 添加商品描述     分表操作
	 * @param tbitemdesc
	 */
	public void insertTbitemdesc(Tbitemdesc tbitemdesc);
	
	
	/**
	 * 根据商品描述id查找商品描述
	 * @param id	商品ID
	 * @return		商品描述
	 */
	public Tbitemdesc selectTbitemDescById(long id);
}