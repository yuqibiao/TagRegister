package com.afrid.tag_register.bean.json;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/11/10
 */

public class SaveTagRequest {


    private RequestDataBean requestData;

    public RequestDataBean getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestDataBean requestData) {
        this.requestData = requestData;
    }

    public static class RequestDataBean {
        private int tagType;
        private List<String> tagList;

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }

        public List<String> getTagList() {
            return tagList;
        }

        public void setTagList(List<String> tagList) {
            this.tagList = tagList;
        }
    }
}
