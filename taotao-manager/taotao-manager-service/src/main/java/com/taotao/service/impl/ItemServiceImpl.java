package com.taotao.service.impl;

import com.common.pojo.EasyUIDataGridResult;
import com.common.utils.IDUtils;
import com.common.utils.JsonUtils;
import com.common.utils.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbitemMapper;
import com.taotao.mapper.TbitemcatMapper;
import com.taotao.mapper.TbitemdescMapper;
import com.taotao.pojo.Tbitem;
import com.taotao.pojo.Tbitemcat;
import com.taotao.pojo.Tbitemdesc;
import com.taotao.service.ItemService;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${BASE}")
    private String BASE;
    @Value("${DESC}")
    private String DESC;
    @Value("${PARAM}")
    private String PARAM;

    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQTopic topic;
    @Autowired
    private TbitemMapper tbItemMapper;
    @Autowired
    private TbitemdescMapper tbitemdescMapper;



    @Autowired
    private TbitemcatMapper tbitemcatMapper;


    @Override
    public Tbitem getItemById(long itemId) {
        //从缓存中取数据
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + BASE);
            //判断不为null 并且 不为 ""
            if(StringUtils.isNotBlank(json)){
                Tbitem tbItem = JsonUtils.jsonToPojo(json, Tbitem.class);
                return tbItem;
            }
        }catch (Exception e){

        }
        Tbitem tbitem = tbItemMapper.getItemById(itemId);
        //吧数据库中的数据加入缓存
        try {
            jedisClient.set(ITEM_INFO + ":" + itemId + BASE, JsonUtils.objectToJson(tbitem));
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbitem;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //使用分页插件
        PageHelper.startPage(page,rows);
        //调用mapper查询数据库
        List<Tbitem> itemList = tbItemMapper.getItemList();
        //通过商品的代码查询得到所有的商品信息 吧他作为参数丢入到 mybatis的分页插件对象里面 自动分页
        PageInfo<Tbitem> info = new PageInfo<Tbitem>(itemList);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        //从分页插件里面得到结果集
        result.setTotal(info.getTotal());
        //得到结果集
        result.setRows(itemList);

        return result;
    }

    @Override
    public TaotaoResult addItem(Tbitem tbitem, String desc) {
        /**
         * 页面传递过来：cid		title	sellpoint	price	num		barcode		uploadFile		desc
         * 手动添加： id  status created updated
         */
        final long itemId = IDUtils.genItemId();
        tbitem.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        tbitem.setStatus((byte) 1);
        Date date = new Date();
        tbitem.setCreated(date);
        tbitem.setUpdated(date);
        // 3、向商品表插入数据
        tbItemMapper.insertIbItem(tbitem);
        // 4、创建一个TbItemDesc对象		分表
        Tbitemdesc itemDesc = new Tbitemdesc();
        itemDesc.setItemid(itemId);
        itemDesc.setItemdesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        tbitemdescMapper.insertTbitemdesc(itemDesc);
        //发送一个商品添加消息
        //		/**
        //		 * 	在这里发布消息 更新缓存
        //		 * 	1.用点对点还是 订阅与发布(因为以后 有可能要同步其他地方，比如订单，购物车。。。)
        //		 * 	2.发送过去的数据是什么类型的数据 格式是什么？
        //		 * 		只发送id（）
        //		 * 		发送json
        //		 */
        jmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                System.out.println(this.getClass() + "====>" + "添加商品更新缓存");
                return textMessage;
            }
        });
        return TaotaoResult.build(200, "保存成功！");
    }

    @Override
    public TaotaoResult deleteItem(long[] ids) {
        tbItemMapper.deleteIbItem(ids);
        return TaotaoResult.build(200, "删除成功！");
    }

    @Override
    public TaotaoResult selectInstockDownItem(long[] ids) {
        tbItemMapper.selectInstockDownItem(ids);
        return TaotaoResult.build(200, "商品下架成功！");
    }

    @Override
    public TaotaoResult selectInstockUpItem(long[] ids) {
        tbItemMapper.selectInstockUpItem(ids);
        return TaotaoResult.build(200, "商品上架成功！");
    }

    @Override
    public TaotaoResult selectTbItemdescById(long id) {
        Tbitemdesc tbitemdesc = tbitemdescMapper.selectTbitemDescById(id);
        return TaotaoResult.build(200, "查询商品描述成功！", tbitemdesc);
    }


    @Override
    public TaotaoResult selectTbitemCidById(long id) {
        Tbitemcat tbitemCat = tbitemcatMapper.getTbitemCat(id);

        return TaotaoResult.build(200, "查询商品描述成功！", tbitemCat);
    }

    @Override
    public Tbitemdesc getItemDescById(long itemId) {
        //从缓存中取数据
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + DESC);
            //判断不为null 并且 不为 ""
            if(StringUtils.isNotBlank(json)){
                Tbitemdesc tbItemDesc = JsonUtils.jsonToPojo(json, Tbitemdesc.class);
                return tbItemDesc;
            }
        }catch (Exception e){

        }

        Tbitemdesc itemDesc = tbitemdescMapper.selectTbitemDescById(itemId);
        try {
            jedisClient.set(ITEM_INFO + ":" + itemId + DESC, JsonUtils.objectToJson(itemDesc));
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemDesc;
    }


}
