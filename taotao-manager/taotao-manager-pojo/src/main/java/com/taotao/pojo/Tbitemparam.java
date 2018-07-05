package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tbitemparam implements Serializable {
    private Long id;

    private Long itemcatid;

    private Date created;

    private Date updated;

    private String paramdata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemcatid() {
        return itemcatid;
    }

    public void setItemcatid(Long itemcatid) {
        this.itemcatid = itemcatid;
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