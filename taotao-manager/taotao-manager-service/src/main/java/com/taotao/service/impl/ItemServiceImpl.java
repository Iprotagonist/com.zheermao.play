package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.IDUtils;
import com.common.utils.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbitemMapper;
import com.taotao.mapper.TbitemcatMapper;
import com.taotao.mapper.TbitemdescMapper;
import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemcat;
import com.taotao.pojo.Tbitemdesc;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbitemMapper tbItemMapper;
	@Autowired
	private TbitemdescMapper tbitemdescMapper;
	@Autowired
	private TbitemcatMapper tbitemcatMapper;
	
	
	@Override
	public Tbitem getItemById(long itemId) {
		Tbitem tbitem = tbItemMapper.getItemById(itemId);
		return tbitem;
	}
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//使用分页插件
		PageHelper.startPage(page, rows);
		//调用mapper查询数据库
		List<Tbitem> itemList = tbItemMapper.getItemList();
		//通过商品的代码查询得到所有的商品信息 吧他作为参数丢入到 mybatis的分页插件对象里面 自动分页
		PageInfo<Tbitem> info = new PageInfo<Tbitem>(itemList);
		//
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		//从分页插件里面得到结果集
		result.setTotal(info.getTotal());
		//得到结果集
		result.setRows(itemList);

		return result;
	}
	@Override
	public TaotaoResult addItem(Tbitem tbitem,String desc) {
		/**
		 * 页面传递过来：cid		title	sellpoint	price	num		barcode		uploadFile		desc
		 * 手动添加： id  status created updated
		 */
		long itemId = IDUtils.genItemId();
		tbitem.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		tbitem.setStatus((byte) 1);
		Date date = new Date();
		tbitem.setCreated(date);
		tbitem.setUpdated(date);
		// 3、向商品表插入数据
		tbItemMapper.insertIbItem(tbitem);
		// 4、创建一个TbItemDesc对象		分表
		Tbitemdesc itemDesc = new Tbitemdesc();
		itemDesc.setItemid(itemId);
		itemDesc.setItemdesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		tbitemdescMapper.insertTbitemdesc(itemDesc);
		

		return TaotaoResult.build(200,"保存成功！");
	}
	@Override
	public TaotaoResult deleteItem(long[] ids) {
		tbItemMapper.deleteIbItem(ids);
		return TaotaoResult.build(200,"删除成功！");
	}
	@Override
	public TaotaoResult selectInstockDownItem(long[] ids) {
		tbItemMapper.selectInstockDownItem(ids);
		return TaotaoResult.build(200,"商品下架成功！");
	}
	@Override
	public TaotaoResult selectInstockUpItem(long[] ids) {
		tbItemMapper.selectInstockUpItem(ids);
		return TaotaoResult.build(200,"商品上架成功！");
	}
	@Override
	public TaotaoResult selectTbItemdescById(long id) {
		Tbitemdesc tbitemdesc = tbitemdescMapper.selectTbitemDescById(id);
		return TaotaoResult.build(200,"查询商品描述成功！",tbitemdesc);
	}
	
	
	@Override
	public TaotaoResult selectTbitemCidById(long id) {
		Tbitemcat tbitemCat = tbitemcatMapper.getTbitemCat(id);
		
		return TaotaoResult.build(200,"查询商品描述成功！",tbitemCat);
	}
	
	

}
