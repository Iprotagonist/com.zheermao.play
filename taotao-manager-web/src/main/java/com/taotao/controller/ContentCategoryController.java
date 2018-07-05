package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.EasyUITreeNode;
import com.common.utils.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(value="id", defaultValue="0") Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}

	/**
	 * 新增内容节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public  TaotaoResult createCategory(long parentId,String name){
		TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	
}
