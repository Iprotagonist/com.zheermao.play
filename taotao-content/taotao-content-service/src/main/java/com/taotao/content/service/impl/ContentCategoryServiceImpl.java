package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojo.EasyUITreeNode;
import com.common.utils.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.Tbcontentcategory;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		//根据分类id查询分类
		List<Tbcontentcategory> tbContentCategorys = tbContentCategoryMapper.findTbContentCategoryById(parentId);
		//创建返回结果集
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		//遍历并且填充返回结果集
		for (Tbcontentcategory tbContentCategory : tbContentCategorys) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsparent()?"closed":"open");
			result.add(node);
		}		
		return result;

	}
	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		Tbcontentcategory tbcontentcategory = new Tbcontentcategory();
		Date date = new Date();
		tbcontentcategory.setParentid(parentId);
		tbcontentcategory.setName(name);
		tbcontentcategory.setStatus(1);
		tbcontentcategory.setSortorder(1);
		tbcontentcategory.setCreated(date);
		tbcontentcategory.setUpdated(date);
		//设置isParent
		//添加一个子节点     如果有父节点
		tbcontentcategory.setIsparent(false);
		tbContentCategoryMapper.insertTbContentCategory(tbcontentcategory);
		
		Tbcontentcategory parentContent = tbContentCategoryMapper.getTbcontentcategoryById(parentId);
		if(!parentContent.getIsparent()){
			//如果原来的节点是子节点  则需把子节点改为父节点
			Tbcontentcategory category = new Tbcontentcategory();
			category.setId(parentId);
			category.setIsparent(true);
			tbContentCategoryMapper.updateContentCategory(category);
		}
		return TaotaoResult.ok(tbcontentcategory);
	}
}
