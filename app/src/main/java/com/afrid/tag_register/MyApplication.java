package com.afrid.tag_register;

import android.app.Application;

import java.util.List;

/**
 * 功能：自定义Application
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public class MyApplication extends Application{

    private int user_id;
    private String user_name;
    private List<String> readerIdList;
    private String currentReaderId;
    private String checkedLinenName;
    private int checkedLinenTypeId;
    private String checkedTrainCode;
    private int checkedSupplierId;
    private String checkedSupplierName;
    private int checkedWarehouseId;
    private int checkedWashFactoryId;
    private String checkedWashFactoryName;
    private int checkHospitalId;
    private String checkHospitalName;
    private int checkedOfficeId;
    private String checkedOfficeName;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getReaderIdList() {
        return readerIdList;
    }

    public void setReaderIdList(List<String> readerIdList) {
        this.readerIdList = readerIdList;
    }

    public String getCurrentReaderId() {
        return currentReaderId;
    }

    public void setCurrentReaderId(String currentReaderId) {
        this.currentReaderId = currentReaderId;
    }

    public int getCheckedLinenTypeId() {
        return checkedLinenTypeId;
    }

    public void setCheckedLinenTypeId(int checkedLinenTypeId) {
        this.checkedLinenTypeId = checkedLinenTypeId;
    }

    public String getCheckedTrainCode() {
        return checkedTrainCode;
    }

    public void setCheckedTrainCode(String checkedTrainCode) {
        this.checkedTrainCode = checkedTrainCode;
    }

    public int getCheckHospitalId() {
        return checkHospitalId;
    }

    public void setCheckHospitalId(int checkHospitalId) {
        this.checkHospitalId = checkHospitalId;
    }

    public int getCheckedSupplierId() {
        return checkedSupplierId;
    }

    public void setCheckedSupplierId(int checkedSupplierId) {
        this.checkedSupplierId = checkedSupplierId;
    }

    public int getCheckedWarehouseId() {
        return checkedWarehouseId;
    }

    public void setCheckedWarehouseId(int checkedWarehouseId) {
        this.checkedWarehouseId = checkedWarehouseId;
    }

    public int getCheckedOfficeId() {
        return checkedOfficeId;
    }

    public void setCheckedOfficeId(int checkedOfficeId) {
        this.checkedOfficeId = checkedOfficeId;
    }

    public String getCheckedLinenName() {
        return checkedLinenName;
    }

    public void setCheckedLinenName(String checkedLinenName) {
        this.checkedLinenName = checkedLinenName;
    }

    public int getCheckedWashFactoryId() {
        return checkedWashFactoryId;
    }

    public void setCheckedWashFactoryId(int checkedWashFactoryId) {
        this.checkedWashFactoryId = checkedWashFactoryId;
    }

    public String getCheckedSupplierName() {
        return checkedSupplierName;
    }

    public void setCheckedSupplierName(String checkedSupplierName) {
        this.checkedSupplierName = checkedSupplierName;
    }

    public String getCheckedWashFactoryName() {
        return checkedWashFactoryName;
    }

    public void setCheckedWashFactoryName(String checkedWashFactoryName) {
        this.checkedWashFactoryName = checkedWashFactoryName;
    }

    public String getCheckHospitalName() {
        return checkHospitalName;
    }

    public void setCheckHospitalName(String checkHospitalName) {
        this.checkHospitalName = checkHospitalName;
    }

    public String getCheckedOfficeName() {
        return checkedOfficeName;
    }

    public void setCheckedOfficeName(String checkedOfficeName) {
        this.checkedOfficeName = checkedOfficeName;
    }
}
