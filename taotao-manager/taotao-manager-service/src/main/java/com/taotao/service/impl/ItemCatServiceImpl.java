package com.taotao.service.impl;

import com.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbitemcatMapper;
import com.taotao.pojo.Tbitemcat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbitemcatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {
        //根据商品
        List<Tbitemcat> catList = tbItemCatMapper.getCatList(parentId);
        //定义返回结果集
        List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
        for (Tbitemcat cat : catList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            //1代表有节点 true
            node.setState(cat.getIsparent() ? "closed" : "open");
            result.add(node);
        }
        return result;
    }

}
