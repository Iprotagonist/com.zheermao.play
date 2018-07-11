package com.taotao.controller;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.TaotaoResult;
import com.taotao.pojo.Tbitem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public Tbitem getItemById(@PathVariable long itemId) {
        Tbitem tbitem = itemService.getItemById(itemId);
        return tbitem;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(int page, int rows) {
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveItem(Tbitem item, String desc) {

        TaotaoResult result = itemService.addItem(item, desc);
        return result;
    }

}
