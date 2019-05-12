package com.afrid.tag_register.net;

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
import com.afrid.tag_register.net.api.NetApi;
import com.yyyu.baselibrary.utils.MyLog;

import java.util.List;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：网络请求API的统一管理类，和其它组件进行交互
 *
 * @author yu
 * @version 1.0
 * @date 2017/8/10
 */

public class APIMethodManager {


    private NetApi netApi;

    private APIMethodManager() {
        APIFactory apiFactory = APIFactory.getInstance();
        netApi = apiFactory.createKunmingApi();
    }

    private static class SingletonHolder {
        private static final APIMethodManager INSTANCE = new APIMethodManager();
    }

    public static APIMethodManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 得到所有供应商
     *
     * @param callback
     * @return
     */
    public Subscription getProviders(final IRequestCallback<GetSupplierReturn> callback) {

        Subscription subscribe = netApi.getAllSupplier()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetSupplierReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetSupplierReturn getProviderReturn) {
                        callback.onSuccess(getProviderReturn);
                    }
                });

        return subscribe;
    }

    /**
     * 得到医院对应的科室
     *
     * @param hospitalId
     * @param callback
     * @return
     */
    public Subscription getHospitalOffice(Integer hospitalId, final IRequestCallback<GetHospitalOfficeReturn> callback) {
        Subscription subscribe = netApi.getHospitalOffice(hospitalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetHospitalOfficeReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetHospitalOfficeReturn getHospitalOfficeReturn) {
                        callback.onSuccess(getHospitalOfficeReturn);
                    }
                });

        return subscribe;
    }

    /**
     * 得到医院对应的布草类型
     *
     * @param hospitalId
     * @param callback
     * @return
     */
    public Subscription getLinenByHospitalId(Integer hospitalId, final IRequestCallback<GetHospitalLinenReturn> callback) {

        Subscription subscribe = netApi.getLinenByHospitalId(hospitalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetHospitalLinenReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetHospitalLinenReturn getHospitalLinenReturn) {
                        callback.onSuccess(getHospitalLinenReturn);
                    }
                });

        return subscribe;
    }

    /**
     * 得到用户所管理的医院
     *
     * @param userId
     * @param callback
     * @return
     */
    public Subscription getHospitalByUserId(Integer userId, final IRequestCallback<GetHospitalReturn> callback) {

        Subscription subscribe = netApi.getHospitalByUserId(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetHospitalReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetHospitalReturn data) {
                        callback.onSuccess(data);
                    }
                });
        return subscribe;
    }

    /**
     * 一天以内提交的标签验重
     *
     * @param request
     * @param callback
     * @return
     */
    public Subscription verifyTagBeforeSaveReceipt(String request, final IRequestCallback<VerifyTagReturn> callback) {
        MyLog.e("request===" + request);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.verifyTagBeforeSaveReceipt(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VerifyTagReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(VerifyTagReturn verifyTagReturn) {
                        callback.onSuccess(verifyTagReturn);
                    }
                });
        return subscribe;
    }

    /**
     * 保存收据
     *
     * @param request  SaveReceiptRequest对应的json
     * @param callback
     */
    public Subscription saveReceipt(String request, final IRequestCallback<BaseJsonResult<String>> callback) {
        MyLog.e("request===" + request);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.saveReceipt(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<String> baseJsonResult) {
                        callback.onSuccess(baseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 得到所有车次信息
     *
     * @param callback
     * @return
     */
    public Subscription getAllTrain(final IRequestCallback<BaseJsonResult<List<ServiceTTrain>>> callback) {
        Subscription subscribe = netApi.getAllTrain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<List<ServiceTTrain>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<List<ServiceTTrain>> listBaseJsonResult) {
                        callback.onSuccess(listBaseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 得到所有布草
     *
     * @return
     */
    public Subscription getAllLinen(final IRequestCallback<BaseJsonResult<List<ServiceTLinen>>> callback) {
        Subscription subscribe = netApi.getAllLinen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<List<ServiceTLinen>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<List<ServiceTLinen>> listBaseJsonResult) {
                        callback.onSuccess(listBaseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 保存标签
     *
     * @param callback
     * @return
     */
    public Subscription saveAllTags(String request, final IRequestCallback<BaseJsonResult<String>> callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.saveTags(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<String> stringBaseJsonResult) {
                        callback.onSuccess(stringBaseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 保存标签 v2(生成订单)
     *
     * @param callback
     * @return
     */
    public Subscription saveAllTagsV2(String request, final IRequestCallback<BaseJsonResult<String>> callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.saveTagsV2(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<String> stringBaseJsonResult) {
                        callback.onSuccess(stringBaseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 保存标签
     *
     * @param callback
     * @return
     */
    public Subscription saveAllTagsV3(String request, final IRequestCallback<BaseJsonResult<String>> callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.saveTagsV3(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<String> stringBaseJsonResult) {
                        callback.onSuccess(stringBaseJsonResult);
                    }
                });
        return subscribe;
    }

    /**
     * 得到某一仓库（车厢）里面的标签信息
     *
     * @param warehouseId
     * @param callback
     * @return
     */
    public Subscription getLinenNumByWarehouseId(Integer warehouseId, final IRequestCallback<BaseJsonResult<List<ServiceTWarehouseLinen>>> callback) {

        Subscription subscribe = netApi.getLinenNumByWarehouseId(warehouseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseJsonResult<List<ServiceTWarehouseLinen>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(BaseJsonResult<List<ServiceTWarehouseLinen>> listBaseJsonResult) {
                        callback.onSuccess(listBaseJsonResult);
                    }
                });
        return subscribe;

    }

    /**
     * 通过仓库名获取仓库信息
     *
     * @param warehouseName
     * @param callback
     * @return
     */
    public Subscription getWarehouseByName(String warehouseName, final IRequestCallback<GetWarehouseByNameReturn> callback) {
        Subscription subscribe = netApi.getWarehouseByName(warehouseName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWarehouseByNameReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetWarehouseByNameReturn getWarehouseByNameReturn) {
                        callback.onSuccess(getWarehouseByNameReturn);
                    }
                });

        return subscribe;
    }

    /**
     * 得到标签的信息
     *
     * @param request  GetTagInfoRequest对应的json
     * @param callback
     */
    public Subscription getTagInfoList(String request, final IRequestCallback<GetTagInfoListReturn> callback) {
        MyLog.e("request===" + request);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        Subscription subscribe = netApi.getTagInfoList(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetTagInfoListReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetTagInfoListReturn getTagInfoListReturn) {
                        callback.onSuccess(getTagInfoListReturn);
                    }
                });
        return subscribe;
    }

    /**
     * 得到用户对应的库房
     *
     * @param callback
     */
    public Subscription getWarehouse(Integer userId, final IRequestCallback<GetWarehouseReturn> callback) {
        MyLog.e("request===" + userId);
        Subscription subscribe = netApi.getWarehouse(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWarehouseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetWarehouseReturn getWarehouseReturn) {
                        callback.onSuccess(getWarehouseReturn);
                    }
                });
        return subscribe;
    }

    /**
     * 得到用户对应的洗涤厂
     *
     * @param userId
     * @param callback
     * @return
     */
    public Subscription getWashFactory(Integer userId, final IRequestCallback<GetWarehouseReturn> callback) {

        Subscription subscribe = netApi.getWashFactory(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWashFactoryReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetWashFactoryReturn getWashFactoryReturn) {
                        callback.onSuccess(getWashFactoryReturn);
                    }
                });
        return subscribe;

    }


    /**
     * 得到所有的洗涤厂
     *
     * @param callback
     * @return
     */
    public Subscription getAllWashFactory(final IRequestCallback<GetWarehouseReturn> callback) {

        Subscription subscribe = netApi.getAllWashFactory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetWashFactoryReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(GetWashFactoryReturn getWashFactoryReturn) {
                        callback.onSuccess(getWashFactoryReturn);
                    }
                });
        return subscribe;

    }



    /**
     * 登录
     *
     * @param request  LoginRequest对应的json
     * @param callback
     */
    public Subscription login(String request, final IRequestCallback<LoginReturn> callback) {
        MyLog.e("request===" + request);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
        MyLog.e("body===" + body);
        Subscription subscribe = netApi.login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(LoginReturn loginReturn) {
                        callback.onSuccess(loginReturn);
                    }
                });
        return subscribe;
    }


}
