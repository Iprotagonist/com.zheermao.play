package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.Tbcontent;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult findAllContentCategoryById(long categoryId){
		EasyUIDataGridResult result = contentService.findContentAll(categoryId);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContetn(Tbcontent tbcontent){
		TaotaoResult addContent = contentService.addContent(tbcontent);
		return addContent;
	}
}
