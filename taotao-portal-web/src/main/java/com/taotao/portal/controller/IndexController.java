package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.Tbcontent;
import com.taotao.portal.pojo.Ad1Node;

@Controller
public class IndexController {
	@Value("${AD1_CID}")
	private Long AD1_CID;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;

	@Autowired
	private ContentService ContentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		List<Tbcontent> contentList = ContentService.getContentList(AD1_CID);
		List<Ad1Node> nodes = new ArrayList<Ad1Node>();
		for (Tbcontent content : contentList) {
			Ad1Node ad1Node = new Ad1Node();
			ad1Node.setSrcB(content.getPic2());
			ad1Node.setHeight(AD1_HEIGHT);
			ad1Node.setWidth(AD1_WIDTH);
			ad1Node.setAlt(content.getTitledesc());
			
			ad1Node.setWidthB(AD1_WIDTH_B);
			ad1Node.setSrc(content.getPic());
			ad1Node.setHref(content.getUrl());
			ad1Node.setHeightB(AD1_HEIGHT_B);
			nodes.add(ad1Node);
		}
		//需要转为json对象
		model.addAttribute("ad1",JsonUtils.objectToJson(nodes));
		
		return "index";
	}
	
	
	
}