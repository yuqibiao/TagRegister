package com.afrid.tag_register.bean.json.return_data;

import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/10/19
 */

public class VerifyTagReturn {

    private int resultCode;
    private String msg;
    private List<VerifyTagBean> resultData;

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

    public List<VerifyTagBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<VerifyTagBean> resultData) {
        this.resultData = resultData;
    }

    public class VerifyTagBean {

        private String receiptSubId;
        private String tagId;
        private Date createTime;

        public VerifyTagBean() {

        }

        public String getReceiptSubId() {
            return receiptSubId;
        }

        public void setReceiptSubId(String receiptSubId) {
            this.receiptSubId = receiptSubId;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }


}
