package com.afrid.tag_register.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afrid.swingu.utils.SwingUManager;
import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.adapter.ProviderAdapter;
import com.afrid.tag_register.bean.json.return_data.GetProviderReturn;
import com.afrid.tag_register.bean.json.return_data.GetSupplierReturn;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.afrid.tag_register.ui.activity.BTDeviceScanActivity;
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

public class SupplierChoiceFragment extends MyBaseFragment{

    @BindView(R.id.rv_provider)
    RecyclerView rv_provider;
    private ProviderAdapter providerAdapter;
    private APIMethodManager apiMethodManager;
    private List<GetSupplierReturn.ResultDataBean> resultData;
    private MyApplication application;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_provider_choice;
    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        application = (MyApplication) getActivity().getApplication();
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    protected void initView() {
        rv_provider.setLayoutManager(new GridLayoutManager(getContext(), 3));
        providerAdapter = new ProviderAdapter(getContext());
        rv_provider.setAdapter(providerAdapter);
    }

    @Override
    protected void initListener() {
        rv_provider.addOnItemTouchListener(new OnRvItemClickListener(rv_provider) {
            @Override
            public void onItemClick(View itemView, int position) {
                //---判断设备是否为用户绑定的
              /*  if (!application.getReaderIdList().contains(application.getCurrentReaderId())) {
                    MyToast.showShort(getContext(), resourceUtils.getStr(R.string.swing_connect_refuse));
                    BTDeviceScanActivity.startAction(getActivity());
                    return;
                }*/

                if (!SwingUManager.getInstance(getContext()).isConnected()) {
                    MyToast.showShort(getContext(), resourceUtils.getStr(R.string.swing_disconnect));
                    BTDeviceScanActivity.startAction(getActivity());
                    return;
                }
                GetSupplierReturn.ResultDataBean resultDataBean = resultData.get(position);
                application.setCheckedSupplierId(resultDataBean.getWarehouseId());//提交上去的是supplier对应的warehouseId
                application.setCheckedSupplierName(resultDataBean.getSupplierName());
                //-- 跳转到医院和类型选择
                Intent intent = new Intent(getActivity() , ZhengZhouTagTypeChoiceActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View itemView, int position) {
            }
        });
    }

    @Override
    protected void initData() {
        showLoadDialog(resourceUtils.getStr(R.string.data_loading));
        apiMethodManager.getProviders(new IRequestCallback<GetSupplierReturn>() {
            @Override
            public void onSuccess(GetSupplierReturn result) {
                int resultCode = result.getResultCode();
                String msg = result.getMsg();
                if (resultCode==200){
                    resultData = result.getResultData();
                    providerAdapter.setDataBeanList(resultData);
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
