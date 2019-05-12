
package com.afrid.tag_register.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.bean.json.BaseJsonResult;
import com.afrid.tag_register.bean.json.SaveTagRequest;
import com.afrid.tag_register.bean.json.SaveTagsRequestV2;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.afrid.swingu.utils.SwingUManager;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.view.WhewView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

/**
 * 布草扫描界面
 */
public class ScanLinenActivity extends MyBaseActivity {

    private static final String TAG = "ScanLinenActivity";

    @BindView(R.id.tv_linen_type)
    TextView tv_linen_type;
    @BindView(R.id.tv_supplier)
    TextView tv_supplier;
    @BindView(R.id.tv_hospital)
    TextView tv_hospital;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_wash_factory)
    TextView tv_wash_factory;
    @BindView(R.id.tv_tag_num)
    TextView tv_tag_num;
    @BindView(R.id.tb_scan)
    Toolbar tb_scan;
    @BindView(R.id.wv_scan)
    WhewView wvScan;

    //存储标签id的set
    private HashSet<String> tagIdSet = new HashSet<>();
    //存储标签id的list
    private List<String> tagIdList;
    private SwingUManager swingUManager;
    private String linenName;
    private MyApplication application;
    private APIMethodManager apiMethodManager;

    @Override
    public void beforeInit() {
        super.beforeInit();
        swingUManager = SwingUManager.getInstance(this);
        application = (MyApplication) getApplication().getApplicationContext();
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_linen;
    }

    @Override
    protected void initView() {
        linenName = application.getCheckedLinenName();
        String checkedSupplierName = application.getCheckedSupplierName();
        String checkHospitalName = application.getCheckHospitalName();
        String checkedOfficeName = application.getCheckedOfficeName();
        String checkedWashFactoryName = application.getCheckedWashFactoryName();
        tv_linen_type.setText(linenName);
        tv_supplier.setText(checkedSupplierName);
        if (!TextUtils.isEmpty(checkHospitalName)){
            tv_hospital.setText(checkHospitalName);
            tv_office.setText(checkedOfficeName);
        }else{
            tv_hospital.setVisibility(View.GONE);
            tv_office.setVisibility(View.GONE);
        }
        tv_wash_factory.setText(checkedWashFactoryName);
    }

    @Override
    protected void initListener() {
        swingUManager.setOnReadResultListener(new SwingUManager.OnReadResultListener() {
            @Override
            public void onRead(String tagId) {
                boolean isAdd = tagIdSet.add(tagId.substring(4));
                if (isAdd) {
                    tv_tag_num.setText("标签数量：" + tagIdSet.size());
                    MyLog.e(TAG, tagId.substring(4) + "   size" + tagIdSet.size());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //---TODO stop操作
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 开始扫描
     */
    public void startScan(View view) {
        tagIdSet.clear();
        //---判断设备是否为用户绑定的
       /* if (!application.getReaderIdList().contains(application.getCurrentReaderId())) {
            MyToast.showShort(this, resourceUtils.getStr(R.string.swing_connect_refuse));
            BTDeviceScanActivity.startAction(this);
            return;
        }*/

        if (!SwingUManager.getInstance(this).isConnected()) {
            MyToast.showShort(this, resourceUtils.getStr(R.string.swing_disconnect));
            BTDeviceScanActivity.startAction(this);
            return;
        }
        wvScan.start();
        swingUManager.resetReader();
        swingUManager.startReader();
    }

    /**
     * 结束扫描
     */
    public void stopScan(View view) {
        wvScan.stop();
        swingUManager.stopReader();
        tagIdList = new ArrayList<>(tagIdSet);

/*        tagIdList.add("000010");
        tagIdList.add("000011");*/

        StringBuilder sbTip = new StringBuilder();
        sbTip.append("布草类型             ：" + linenName);
        sbTip.append("\r\n");
        sbTip.append("布草数量             ：" + tagIdList.size());
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("扫描结果")
                .setMessage(sbTip.toString())
                .setNegativeButton("确认提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(client==Client.HONG_KONG){
                            saveTag();
                        }else{
                            saveTagV2();
                        }
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyToast.showShort(ScanLinenActivity.this, "已取消操作");
                    }
                })
                .create();
        alertDialog.show();

    }

    private void saveTag() {
        SaveTagRequest request = new SaveTagRequest();
        SaveTagRequest.RequestDataBean requestDataBean = new SaveTagRequest.RequestDataBean();
        requestDataBean.setTagType(application.getCheckedLinenTypeId());
        requestDataBean.setTagList(tagIdList);
        request.setRequestData(requestDataBean);
        showLoadDialog(resourceUtils.getStr(R.string.scan_add_tag_tip));
        apiMethodManager.saveAllTags(mGson.toJson(request), new IRequestCallback<BaseJsonResult<String>>() {
            @Override
            public void onSuccess(BaseJsonResult<String> result) {
                int resultCode = result.getResultCode();
                String msg = result.getMsg();
                if (resultCode == 200) {
                    MyToast.showLong(ScanLinenActivity.this, "" + msg);
                    finish();
                }else{
                    MyToast.showLong(ScanLinenActivity.this, "" + msg);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyToast.showLong(ScanLinenActivity.this, "" + resourceUtils.getStr(R.string.net_error));
                throwable.printStackTrace();
                MyLog.e("异常：" + throwable.getMessage());
                hiddenLoadingDialog();
            }
        });
    }

    private void saveTagV2() {
        SaveTagsRequestV2 request = new SaveTagsRequestV2();
        SaveTagsRequestV2.RequestDataBean requestDataBean = new SaveTagsRequestV2.RequestDataBean();
        requestDataBean.setTagType(application.getCheckedLinenTypeId());
        requestDataBean.setTagList(tagIdList);
        SaveTagsRequestV2.RequestDataBean.ReceiptVoBean receiptVoBean = new SaveTagsRequestV2.RequestDataBean.ReceiptVoBean();
        receiptVoBean.setLinenNum(tagIdList.size());
        receiptVoBean.setUserId(application.getUser_id());
        receiptVoBean.setOfficeId(application.getCheckedOfficeId());
        receiptVoBean.setReaderId(application.getCurrentReaderId());
        receiptVoBean.setSenderWarehouseId(application.getCheckedSupplierId());//senderId为供应商对应的warehouseId
        receiptVoBean.setReceiptWarehouseId(application.getCheckedWashFactoryId());//receiptId为洗涤厂Id
        receiptVoBean.setCustomerId(application.getCheckedWarehouseId());
        receiptVoBean.setWarehouseId(application.getCheckedWarehouseId());
        requestDataBean.setReceiptVo(receiptVoBean);
        request.setRequestData(requestDataBean);
        showLoadDialog(resourceUtils.getStr(R.string.scan_add_tag_tip));
        // v2 和 v3 参数相同 修改此处即可
        apiMethodManager.saveAllTagsV3(mGson.toJson(request), new IRequestCallback<BaseJsonResult<String>>() {
            @Override
            public void onSuccess(BaseJsonResult<String> result) {
                int resultCode = result.getResultCode();
                String msg = result.getMsg();
                if (resultCode == 200) {
                    MyToast.showLong(ScanLinenActivity.this, "" + msg);
                    finish();
                    EventBus.getDefault().post("close");
                }else{
                    MyToast.showLong(ScanLinenActivity.this, "" + msg);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyToast.showLong(ScanLinenActivity.this, "" + resourceUtils.getStr(R.string.net_error));
                throwable.printStackTrace();
                MyLog.e("异常：" + throwable.getMessage());
                hiddenLoadingDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReceiptActivity.RECEIPT_REQUEST_CODE
                && resultCode == ReceiptActivity.RECEIPT_RESULT_CODE) {
            //正常的提交receipt后清空数据
            tagIdSet.clear();
            tagIdList.clear();
        }
    }

    public void back(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        wvScan.stop();
        swingUManager.stopReader();
        super.onDestroy();
    }

    /**
     * 页面跳转
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ScanLinenActivity.class);
        activity.startActivity(intent);
    }
}
