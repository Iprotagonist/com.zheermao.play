package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.Tbcontent;

public interface TbcontentMapper {
	
	/**
	 *	根据分类id查询分类集合
	 */
	public List<Tbcontent> findTbContentAll(long categoryId);
	
	/**
	 * 添加一个内容信息到数据库
	 * @param tbcontent
	 */
	public void insertContent(Tbcontent tbcontent);
	
}