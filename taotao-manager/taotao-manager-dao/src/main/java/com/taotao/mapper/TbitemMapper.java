package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.Tbitem;

public interface TbitemMapper {
	/**
	 * 根据商品id查询商品
	 * @param id
	 * @return
	 */
	public Tbitem getItemById(long id);
	/**
	 * 分页查询商品信息
	 * @return 返回当前页的记录条数
	 */
	public List<Tbitem> getItemList();
	
	
	/**
	 * 添加一个商品到数据库
	 * @param tbitem
	 */
	public void insertIbItem(Tbitem tbitem);
	/**
	 * 根据id删除商品
	 * @param id	商品id
	 */
	public void deleteIbItem(long[] ids);
	/**
	 * 根据id下架商品
	 * @param id	商品id
	 */
	public void selectInstockDownItem(long[] ids);
	/**
	 * 根据id上架商品
	 * @param id	商品id
	 */
	public void selectInstockUpItem(long[] ids);
}