package com.taotao.service;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.TaotaoResult;
import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemcat;

public interface ItemService {
	/**
	 * 根据商品id查询商品信息
	 * @param itemId
	 * @return
	 */
	public Tbitem getItemById(long itemId);
	/**
	 * 分页显示商品信息
	 * @param page 当前页
	 * @param rows 每页显示条数 默认30条
	 * @return 返回当前页面的记录条数，注意必须是json类型
	 */
	public EasyUIDataGridResult getItemList(int page, int rows);
	
	/**
	 * 添加商品信息     有些数据需手动添加
	 * @param tbitem  商品对象
	 * @param desc   商品描述
	 * @return	结果集      	状态码、消息、数据。
	 */
	public TaotaoResult addItem(Tbitem tbitem, String desc);
	/**
	 * 根据ID删除tbitem商品
	 * @param ids	商品id数组
	 * @return	结果集
	 */
	public TaotaoResult deleteItem(long[] ids);
	/**
	 * 根据ID下架tbitem商品
	 * @param ids	商品id数组
	 * @return	结果集
	 */
	public TaotaoResult selectInstockDownItem(long[] ids);
	/**
	 * 根据ID上架tbitem商品
	 * @param ids	商品id数组
	 * @return	结果集
	 */
	public TaotaoResult selectInstockUpItem(long[] ids);
	
	/**
	 * 根据商品id查询商品描述
	 * @param id
	 * @return
	 */
	public TaotaoResult selectTbItemdescById(long id);
	
	/**
	 * 根据tbitemcat表   id   查询所属类名
	 * @param id
	 * @return
	 */
	public TaotaoResult selectTbitemCidById(long id);
	
	
}
