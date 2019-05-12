package com.afrid.tag_register.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.adapter.WarehouseAdapter;
import com.afrid.tag_register.bean.json.return_data.GetWarehouseReturn;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;

import java.util.List;

import butterknife.BindView;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/20
 */

public class WarehouseChoiceActivity extends MyBaseActivity {

    private static final String TAG = "WashFactoryChoiceActivi";
    public static final int REQUEST_WASH_FACTORY = 101;

    @BindView(R.id.rv_wash_factory)
    RecyclerView rv_warehouse;

    private APIMethodManager apiMethodManager;
    private WarehouseAdapter warehouseAdapter;
    private List<GetWarehouseReturn.ResultDataBean> resultData;
    private MyApplication application;

    @Override
    public int getLayoutId() {
        return R.layout.activity_warehouse_choice;
    }

    @Override
    public void beforeInit() {
        super.beforeInit();
        apiMethodManager = APIMethodManager.getInstance();
        application = (MyApplication) getApplication();
    }

    @Override
    protected void initView() {
        rv_warehouse.setLayoutManager(new GridLayoutManager(this, 2));
        warehouseAdapter = new WarehouseAdapter(this);
        rv_warehouse.setAdapter(warehouseAdapter);
    }

    @Override
    protected void initListener() {
        rv_warehouse.addOnItemTouchListener(new OnRvItemClickListener(rv_warehouse) {
            @Override
            public void onItemClick(View itemView, int position) {
                application.setCheckedWarehouseId(resultData.get(position).getWarehouseId());
                ScanLinenActivity.startAction(WarehouseChoiceActivity.this);
                finish();
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showLoadDialog(resourceUtils.getStr(R.string.data_loading));
        apiMethodManager.getWashFactory(application.getUser_id(), new IRequestCallback<GetWarehouseReturn>() {
            @Override
            public void onSuccess(GetWarehouseReturn result) {
                MyLog.e(TAG, "result===" + result.getMsg());
                int resultCode = result.getResultCode();
                if (resultCode == 200) {
                    resultData = result.getResultData();
                    warehouseAdapter.setDataBeanList(resultData);
                } else if (resultCode == 500) {
                    MyToast.show(WarehouseChoiceActivity.this, result.getMsg(), Toast.LENGTH_SHORT);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyToast.showShort(WarehouseChoiceActivity.this, resourceUtils.getStr(R.string.net_error));
                hiddenLoadingDialog();
            }
        });
    }

    public void back(View view) {
        finish();
    }

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, WarehouseChoiceActivity.class);
        activity.startActivity(intent);
    }

}
