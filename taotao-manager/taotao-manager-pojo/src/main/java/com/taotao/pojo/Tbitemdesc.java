package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tbitemdesc implements Serializable {
    private Long itemid;

    private Date created;

    private Date updated;

    private String itemdesc;

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

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc == null ? null : itemdesc.trim();
    }

	@Override
	public String toString() {
		return "Tbitemdesc [itemid=" + itemid + ", created=" + created + ", updated=" + updated + ", itemdesc="
				+ itemdesc + "]";
	}
    
}