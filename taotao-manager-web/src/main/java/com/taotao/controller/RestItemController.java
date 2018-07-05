package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.TaotaoResult;
import com.taotao.pojo.Tbitemcat;
import com.taotao.service.ItemService;


@Controller
@RequestMapping("/rest/item")
public class RestItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 删除商品   
	 * @param params 商品ID
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteItemById(@RequestParam("ids") String sIds ){
		long[] ids = stringArrToLongArr(sIds);
		TaotaoResult deleteItem = itemService.deleteItem(ids);
		return deleteItem;
	}
	/**
	 * 下架商品   
	 * @param params 商品ID
	 */
	@RequestMapping("/instock")
	@ResponseBody
	public TaotaoResult instockDownItemById(@RequestParam("ids")  String sIds  ){
		long[] ids = stringArrToLongArr(sIds);
		TaotaoResult instockDown = itemService.selectInstockDownItem(ids);
		return instockDown;
	}
	/**
	 * 上架商品   
	 * @param params 商品ID
	 */
	@RequestMapping("/reshelf")
	@ResponseBody
	public TaotaoResult instockUpItemById(@RequestParam("ids") String sIds  ){
		long[] ids = stringArrToLongArr(sIds);
		TaotaoResult instockUp = itemService.selectInstockUpItem(ids);
		return instockUp;
	}
	
	
	
	/**
	 * 编辑商品  	回显商品描述的信息
	 * 表为：tbitemdesc
	 * @param id
	 * @return
	 */
	@RequestMapping("/query/item/desc/{id}")
	@ResponseBody
	public TaotaoResult inseretDesc(@PathVariable String id){
		long lid = Long.parseLong(id);
		TaotaoResult taotaoResult = itemService.selectTbItemdescById(lid);
		return taotaoResult;
	}
	/**
	 * 回显商品cid名称
	 * @param cid
	 * @return
	 */
	@RequestMapping("/query/item/cid/{cid}")
	@ResponseBody
	public TaotaoResult selectTbitemCid(@PathVariable Long cid){
		TaotaoResult taotaoResult = itemService.selectTbitemCidById(cid);
		Tbitemcat tbitemcat = (Tbitemcat) taotaoResult.getData();
		return taotaoResult;
	}
	
	/**
	 * 回显商品规格
	 * @param sid
	 * @return
	@RequestMapping("/param/item/query/{id}")
	@ResponseBody
	public TaotaoResult inseretSpe(@PathVariable String sid){
		long id = Long.parseLong(sid);
		TaotaoResult taotaoResult = itemService.selectTbItemdescById(id);
		System.out.println("taotaoResult = " + taotaoResult.getData().toString());
		return taotaoResult;
	}
	 */
	
	/**
	 * String 数组转 long数组
	 * @param string
	 * @return
	 */
	public long[] stringArrToLongArr(String string){
		String[] split = string.split(",");
		long[] ids = new long[split.length];
        for (int i = 0; i < split.length; i++) {
        	ids[i] = Long.valueOf(split[i]);
        }
		return ids;
	}
	
}
