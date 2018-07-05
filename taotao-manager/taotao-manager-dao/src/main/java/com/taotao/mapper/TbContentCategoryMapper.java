package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.Tbcontentcategory;

public interface TbContentCategoryMapper{
	/**
	 * 查询所有父节点id为parentId的Tbcontentcategory对象
	 * @param parentId
	 * @return
	 */
	public List<Tbcontentcategory> findTbContentCategoryById(long parentId);
	/**
	 * 添加内容分类节点
	 * @param tbcontentcategory
	 */
	public void insertTbContentCategory(Tbcontentcategory tbcontentcategory);
	/**
	 * 根据父类id查询当前分类的父类
	 * @param id	父类id
	 * @return
	 */
	public Tbcontentcategory getTbcontentcategoryById(long id);
	
	/**
	 * 修改内容分类节点
	 * @param tbcontentcategory
	 */
	public void updateContentCategory(Tbcontentcategory tbcontentcategory);
	
}