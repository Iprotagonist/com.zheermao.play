package com.taotao.content.service;

import java.util.List;

import com.common.pojo.EasyUIDataGridResult;
import com.common.pojo.EasyUITreeNode;
import com.common.utils.TaotaoResult;
import com.taotao.pojo.Tbcontent;

public interface ContentService {
	
	/**
	 * 根据内容分类id查询分类
	 * @param categoryId
	 * @return 记录条数    结果集
	 */
	public EasyUIDataGridResult findContentAll(long categoryId);
	
	/**
	 * 添加一个内容到数据库    手动添加参数2个
	 * @param tbcontent
	 * @return   200     结果集
	 */
	public TaotaoResult addContent(Tbcontent tbcontent);
	
	/**
	 * 查询分类下的内容
	 * @param categoryId
	 * @return
	 */
	public List<Tbcontent> getContentList(long categoryId);
}
