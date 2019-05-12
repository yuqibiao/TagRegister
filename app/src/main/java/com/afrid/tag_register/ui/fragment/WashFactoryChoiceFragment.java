package com.afrid.tag_register.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afrid.swingu.utils.SwingUManager;
import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.adapter.WashFactoryAdapter;
import com.afrid.tag_register.bean.json.return_data.GetSupplierReturn;
import com.afrid.tag_register.bean.json.return_data.GetWarehouseReturn;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.afrid.tag_register.ui.activity.BTDeviceScanActivity;
import com.afrid.tag_register.ui.activity.ScanLinenActivity;
import com.afrid.tag_register.ui.activity.ZhengZhouTagTypeChoiceActivity;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;

import java.util.List;

import butterknife.BindView;

/**
 * 功能：供应商选择
 *
 * @author yu
 * @version 1.0
 * @date 2018/1/12
 */

public class WashFactoryChoiceFragment extends MyBaseFragment{

    @BindView(R.id.rv_wash_factory)
    RecyclerView rv_wash_factory;
    private WashFactoryAdapter factoryAdapter;
    private APIMethodManager apiMethodManager;
    private List<GetWarehouseReturn.ResultDataBean> resultData;
    private MyApplication application;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wash_factory_choice;
    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        application = (MyApplication) getActivity().getApplication();
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    protected void initView() {
        rv_wash_factory.setLayoutManager(new GridLayoutManager(getContext(), 3));
        factoryAdapter = new WashFactoryAdapter(getContext());
        rv_wash_factory.setAdapter(factoryAdapter);
    }

    @Override
    protected void initListener() {
        rv_wash_factory.addOnItemTouchListener(new OnRvItemClickListener(rv_wash_factory) {
            @Override
            public void onItemClick(View itemView, int position) {
                //---判断设备是否为用户绑定的
               /* if (!application.getReaderIdList().contains(application.getCurrentReaderId())) {
                    MyToast.showShort(getContext(), resourceUtils.getStr(R.string.swing_connect_refuse));
                    BTDeviceScanActivity.startAction(getActivity());
                    return;
                }*/

              /*  if (!SwingUManager.getInstance(getContext()).isConnected()) {
                    MyToast.showShort(getContext(), resourceUtils.getStr(R.string.swing_disconnect));
                    BTDeviceScanActivity.startAction(getActivity());
                    return;
                }*/
                GetWarehouseReturn.ResultDataBean resultDataBean = resultData.get(position);
                application.setCheckedWashFactoryId(resultDataBean.getWarehouseId());
                application.setCheckedWashFactoryName(resultDataBean.getWarehouseName());
                //跳转到洗涤厂选择界面
                ScanLinenActivity.startAction(getActivity());
            }

            @Override
            public void onItemLongClick(View itemView, int position) {
            }
        });
    }

    @Override
    protected void initData() {
        showLoadDialog(resourceUtils.getStr(R.string.data_loading));
        apiMethodManager.getAllWashFactory(new IRequestCallback<GetWarehouseReturn>() {
            @Override
            public void onSuccess(GetWarehouseReturn result) {
                int resultCode = result.getResultCode();
                String msg = result.getMsg();
                if (resultCode==200){
                    resultData = result.getResultData();
                    factoryAdapter.setDataBeanList(resultData);
                }else{
                    MyToast.showShort(getContext(), msg);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
