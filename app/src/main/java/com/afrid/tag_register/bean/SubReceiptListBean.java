package com.afrid.tag_register.bean;

import java.util.List;

public class SubReceiptListBean {

    private int tagTypeId;
    private String tagTypeName;
    private int tagNum;
    private Integer actualTagNum;//仓库中记录的标签数量（可能返回null）
    private List<String> tagIdList;

    public int getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(int tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }

    public List<String> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<String> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public Integer getActualTagNum() {
        return actualTagNum;
    }

    public void setActualTagNum(Integer actualTagNum) {
        this.actualTagNum = actualTagNum;
    }
}