package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.Tbitemcat;

public interface TbitemcatMapper {
	/**
	 * 根据商品分类parentId查询商品分类
	 * @param parentId 父级目录id
	 * @return 这个当前这个父级目录的商品分类信息
	 */
   public List<Tbitemcat> getCatList(long parentId);
   
   
   
   /**
    * 查询tbitemcat
    * @param id
    * @return
    */
   public Tbitemcat getTbitemCat(long id);
}