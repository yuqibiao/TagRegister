package com.afrid.tag_register.bean.json.return_data;

import com.afrid.tag_register.bean.SubReceiptListBean;

import java.util.List;

/**
 * 功能：得到标签信息返回数据
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public class GetTagInfoListReturn {


    private int resultCode;
    private String msg;
    private ResultDataBean resultData;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {

        private int vaildTagNum;
        private UselessTagBean uselessTag;
        private List<SubReceiptListBean> mIxTagLinenList;

        public int getVaildTagNum() {
            return vaildTagNum;
        }

        public void setVaildTagNum(int vaildTagNum) {
            this.vaildTagNum = vaildTagNum;
        }

        public UselessTagBean getUselessTag() {
            return uselessTag;
        }

        public void setUselessTag(UselessTagBean uselessTag) {
            this.uselessTag = uselessTag;
        }

        public List<SubReceiptListBean> getMIxTagLinenList() {
            return mIxTagLinenList;
        }

        public void setMIxTagLinenList(List<SubReceiptListBean> mIxTagLinenList) {
            this.mIxTagLinenList = mIxTagLinenList;
        }

        public List<SubReceiptListBean> getmIxTagLinenList() {
            return mIxTagLinenList;
        }

        public void setmIxTagLinenList(List<SubReceiptListBean> mIxTagLinenList) {
            this.mIxTagLinenList = mIxTagLinenList;
        }

        public static class UselessTagBean {

            private Object tagTypeId;
            private String tagTypeName;
            private int tagNum;
            private List<String> tagList;

            public Object getTagTypeId() {
                return tagTypeId;
            }

            public void setTagTypeId(Object tagTypeId) {
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

            public List<String> getTagList() {
                return tagList;
            }

            public void setTagList(List<String> tagList) {
                this.tagList = tagList;
            }
        }


        public static class WarehouseLinensBean {
            private Integer linenId;
            private Integer linenNum;

            public WarehouseLinensBean() {
            }

            public Integer getLinenId() {
                return linenId;
            }

            public void setLinenId(Integer linenId) {
                this.linenId = linenId;
            }

            public Integer getLinenNum() {
                return linenNum;
            }

            public void setLinenNum(Integer linenNum) {
                this.linenNum = linenNum;
            }
        }

    }
}
