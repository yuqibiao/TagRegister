package com.afrid.tag_register.bean.json;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2018/1/12
 */

public class SaveTagsRequestV2 {


    private RequestDataBean requestData;

    public RequestDataBean getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestDataBean requestData) {
        this.requestData = requestData;
    }

    public static class RequestDataBean {

        private int tagType;
        private ReceiptVoBean receiptVo;
        private List<String> tagList;

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }

        public ReceiptVoBean getReceiptVo() {
            return receiptVo;
        }

        public void setReceiptVo(ReceiptVoBean receiptVo) {
            this.receiptVo = receiptVo;
        }

        public List<String> getTagList() {
            return tagList;
        }

        public void setTagList(List<String> tagList) {
            this.tagList = tagList;
        }

        public static class ReceiptVoBean {

            private String readerId;
            private int userId;
            private int senderWarehouseId;
            private int receiptWarehouseId;
            private int linenNum;
            private int warehouseId;
            private int officeId;
            private Integer customerId;

            public String getReaderId() {
                return readerId;
            }

            public void setReaderId(String readerId) {
                this.readerId = readerId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSenderWarehouseId() {
                return senderWarehouseId;
            }

            public void setSenderWarehouseId(int senderWarehouseId) {
                this.senderWarehouseId = senderWarehouseId;
            }

            public int getReceiptWarehouseId() {
                return receiptWarehouseId;
            }

            public void setReceiptWarehouseId(int receiptWarehouseId) {
                this.receiptWarehouseId = receiptWarehouseId;
            }

            public int getLinenNum() {
                return linenNum;
            }

            public void setLinenNum(int linenNum) {
                this.linenNum = linenNum;
            }

            public int getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(int warehouseId) {
                this.warehouseId = warehouseId;
            }

            public int getOfficeId() {
                return officeId;
            }

            public void setOfficeId(int officeId) {
                this.officeId = officeId;
            }

            public Integer getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Integer customerId) {
                this.customerId = customerId;
            }
        }
    }
}
