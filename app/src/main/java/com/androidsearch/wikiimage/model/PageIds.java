
package com.androidsearch.wikiimage.model;

import com.google.gson.annotations.SerializedName;

public class PageIds {

    @SerializedName("pageid")
    private Integer pageid;
    @SerializedName("ns")
    private Integer ns;
    @SerializedName("title")
    private String title;
    @SerializedName("index")
    private Integer index;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

}
