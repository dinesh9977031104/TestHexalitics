package com.example.testhexalitics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("helpLineId")
    @Expose
    private Integer helpLineId;
    @SerializedName("helpLineName")
    @Expose
    private String helpLineName;
    @SerializedName("helpLineNo")
    @Expose
    private String helpLineNo;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getHelpLineId() {
        return helpLineId;
    }

    public void setHelpLineId(Integer helpLineId) {
        this.helpLineId = helpLineId;
    }

    public String getHelpLineName() {
        return helpLineName;
    }

    public void setHelpLineName(String helpLineName) {
        this.helpLineName = helpLineName;
    }

    public String getHelpLineNo() {
        return helpLineNo;
    }

    public void setHelpLineNo(String helpLineNo) {
        this.helpLineNo = helpLineNo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
