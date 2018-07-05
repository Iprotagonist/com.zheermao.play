package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tborder implements Serializable {
    private String orderid;

    private String payment;

    private Integer paymenttype;

    private String postfee;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Date paymenttime;

    private Date consigntime;

    private Date endtime;

    private Date closetime;

    private String shippingname;

    private String shippingcode;

    private Long userid;

    private String buyermessage;

    private String buyernick;

    private Integer buyerrate;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPostfee() {
        return postfee;
    }

    public void setPostfee(String postfee) {
        this.postfee = postfee == null ? null : postfee.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Date getConsigntime() {
        return consigntime;
    }

    public void setConsigntime(Date consigntime) {
        this.consigntime = consigntime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public String getShippingname() {
        return shippingname;
    }

    public void setShippingname(String shippingname) {
        this.shippingname = shippingname == null ? null : shippingname.trim();
    }

    public String getShippingcode() {
        return shippingcode;
    }

    public void setShippingcode(String shippingcode) {
        this.shippingcode = shippingcode == null ? null : shippingcode.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getBuyermessage() {
        return buyermessage;
    }

    public void setBuyermessage(String buyermessage) {
        this.buyermessage = buyermessage == null ? null : buyermessage.trim();
    }

    public String getBuyernick() {
        return buyernick;
    }

    public void setBuyernick(String buyernick) {
        this.buyernick = buyernick == null ? null : buyernick.trim();
    }

    public Integer getBuyerrate() {
        return buyerrate;
    }

    public void setBuyerrate(Integer buyerrate) {
        this.buyerrate = buyerrate;
    }
}