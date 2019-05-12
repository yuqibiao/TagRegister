package com.afrid.tag_register.net.api;

import com.afrid.tag_register.bean.ServiceTLinen;
import com.afrid.tag_register.bean.ServiceTTrain;
import com.afrid.tag_register.bean.ServiceTWarehouseLinen;
import com.afrid.tag_register.bean.json.BaseJsonResult;
import com.afrid.tag_register.bean.json.return_data.GetHospitalLinenReturn;
import com.afrid.tag_register.bean.json.return_data.GetHospitalOfficeReturn;
import com.afrid.tag_register.bean.json.return_data.GetHospitalReturn;
import com.afrid.tag_register.bean.json.return_data.GetProviderReturn;
import com.afrid.tag_register.bean.json.return_data.GetSupplierReturn;
import com.afrid.tag_register.bean.json.return_data.GetTagInfoListReturn;
import com.afrid.tag_register.bean.json.return_data.GetWarehouseByNameReturn;
import com.afrid.tag_register.bean.json.return_data.GetWarehouseReturn;
import com.afrid.tag_register.bean.json.return_data.GetWashFactoryReturn;
import com.afrid.tag_register.bean.json.return_data.LoginReturn;
import com.afrid.tag_register.bean.json.return_data.VerifyTagReturn;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 功能：昆明项目网络请求
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public interface NetApi {


    @GET("supplier/v1/getAllSupplier")
    Observable<GetSupplierReturn> getAllSupplier();

    @GET("office/v1/getOfficeByOfficeSuper/{hospitalId}")
    Observable<GetHospitalOfficeReturn> getHospitalOffice(@Path("hospitalId") Integer hospitalId);

    @GET("warehouse/wrapper/v1/getHospitalByUserId/userId/{userId}")
    Observable<GetHospitalReturn> getHospitalByUserId(@Path("userId") Integer userId);

    @GET("hospitalLinen/v1/getLinenByHospitalId/{hospitalId}")
    Observable<GetHospitalLinenReturn> getLinenByHospitalId(@Path("hospitalId") Integer hospitalId);

    @POST("receipt/v1/verifyTagBeforeSaveReceipt")
    Observable<VerifyTagReturn> verifyTagBeforeSaveReceipt(@Body RequestBody requestBody);

    @POST("receipt/v1/saveReceipt")
    Observable<BaseJsonResult<String>> saveReceipt(@Body RequestBody requestBody);

    @POST("tag/v1/getTagInfoList")
    Observable<GetTagInfoListReturn> getTagInfoList(@Body RequestBody requestBody);

    @GET("train/v1/getAllTrain")
    Observable<BaseJsonResult<List<ServiceTTrain>>> getAllTrain();

    @GET("linen/v1/getAllLinen")
    Observable<BaseJsonResult<List<ServiceTLinen>>> getAllLinen();

    @POST("tag/v1/saveTags")
    Observable <BaseJsonResult<String>> saveTags(@Body RequestBody requestBody);

    @POST("tag/v2/saveTags")
    Observable <BaseJsonResult<String>> saveTagsV2(@Body RequestBody requestBody);

    @POST("tag/v3/saveTags")
    Observable <BaseJsonResult<String>> saveTagsV3(@Body RequestBody requestBody);

    @GET("warehouseIdLinen/v1/getLinenNumByWarehouseId/{warehouseId}")
    Observable<BaseJsonResult<List<ServiceTWarehouseLinen>>> getLinenNumByWarehouseId(@Path("warehouseId") Integer warehouseId);

    @GET("warehouse/v1/getWarehouseByName/{warehouseName}")
    Observable<GetWarehouseByNameReturn> getWarehouseByName(@Path("warehouseName") String warehouseName);

    @GET("warehouse/v1/users/{userId}/prop/库房ID")
    Observable<GetWarehouseReturn> getWarehouse(@Path("userId") Integer userId);

    @GET("warehouse/v1/getWashFactory/userId/{userId}")
    Observable<GetWashFactoryReturn> getWashFactory(@Path("userId") Integer userId);

    @GET("warehouse/v1/getAllWashFactory")
    Observable<GetWashFactoryReturn> getAllWashFactory();

    @POST("user/v1/checkUser")
    Observable<LoginReturn> login(@Body RequestBody requestBody);

}
