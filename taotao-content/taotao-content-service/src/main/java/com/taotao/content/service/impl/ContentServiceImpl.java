package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.JsonUtils;
import com.common.utils.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbcontentMapper;
import com.taotao.pojo.Tbcontent;

import sun.tools.jar.resources.jar;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	@Autowired
	private TbcontentMapper tbcontentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Override
	public EasyUIDataGridResult findContentAll(long categoryId) {
		List<Tbcontent> contents = tbcontentMapper.findTbContentAll(categoryId);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(contents.size());
		result.setRows(contents);
		return result;
	}

	@Override
	public TaotaoResult addContent(Tbcontent tbcontent) {
		Date date = new Date();
		tbcontent.setCreated(date);
		tbcontent.setUpdated(date);
		tbcontentMapper.insertContent(tbcontent);
		jedisClient.hdel(CONTENT_KEY, tbcontent.getCategoryId() + "");
		return TaotaoResult.ok();
	}

	@Override
	public List<Tbcontent> getContentList(long categoryId) {
		//取缓存
		try {
			String json = jedisClient.hget(CONTENT_KEY, categoryId + "");
			if(StringUtils.isNotBlank(json)){
				List<Tbcontent> results = JsonUtils.jsonToList(json, Tbcontent.class);
				System.out.println("从缓存中取值");
				return results;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<Tbcontent> results = tbcontentMapper.findTbContentAll(categoryId);
		System.out.println("查询数据库");
		//把list集合变为json
		try {
			jedisClient.hset(CONTENT_KEY, categoryId + "", JsonUtils.objectToJson(results));
			System.out.println("加入缓存");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//加缓存
		return results;
	}

}
