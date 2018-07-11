package com.taotao.search.dao;

import java.util.List;


import com.common.pojo.SearchItem;

public interface SearchItemMapper {
	/**
	 * 查询数据库中的tbitem，tbitemcat，tbitemdesc
	 * @return SearchItem 
	 */
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
