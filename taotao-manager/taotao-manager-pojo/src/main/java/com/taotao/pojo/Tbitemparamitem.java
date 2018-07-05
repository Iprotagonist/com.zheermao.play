package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tbitemparamitem implements Serializable {
    private Long id;

    private Long itemid;

    private Date created;

    private Date updated;

    private String paramdata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamdata() {
        return paramdata;
    }

    public void setParamdata(String paramdata) {
        this.paramdata = paramdata == null ? null : paramdata.trim();
    }
}