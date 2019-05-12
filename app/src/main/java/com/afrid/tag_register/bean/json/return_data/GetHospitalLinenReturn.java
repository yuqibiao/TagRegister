package com.afrid.tag_register.bean.json.return_data;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/11/27
 */

public class GetHospitalLinenReturn {

    private int resultCode;
    private String msg;
    private List<ResultDataBean> resultData;

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

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {

        private int linenId;
        private String linenName;
        private int parentId;

        public int getLinenId() {
            return linenId;
        }

        public void setLinenId(int linenId) {
            this.linenId = linenId;
        }

        public String getLinenName() {
            return linenName;
        }

        public void setLinenName(String linenName) {
            this.linenName = linenName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
