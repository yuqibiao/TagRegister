package com.afrid.tag_register.bean.json.return_data;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/10/19
 */

public class GetWarehouseByNameReturn {


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
        /**
         * warehouseId : 1
         * warehouseName : 布草周转仓01
         * regTime : 1505200179000
         */

        private int warehouseId;
        private String warehouseName;
        private long regTime;

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public long getRegTime() {
            return regTime;
        }

        public void setRegTime(long regTime) {
            this.regTime = regTime;
        }
    }
}
