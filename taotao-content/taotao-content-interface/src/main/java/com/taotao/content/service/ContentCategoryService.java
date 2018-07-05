package com.taotao.content.service;

import java.util.List;

import com.common.pojo.EasyUITreeNode;
import com.common.utils.TaotaoResult;

public interface ContentCategoryService {
	/**
	 * 查询内容分类的节点
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoryList(long parentId);
	
	/**
	 * 添加一个节点内容到数据库
	 * @param parentId	节点父类ID
	 * @param name		节点名称
	 * @return
	 */
	public TaotaoResult addContentCategory(long parentId, String name);
}
