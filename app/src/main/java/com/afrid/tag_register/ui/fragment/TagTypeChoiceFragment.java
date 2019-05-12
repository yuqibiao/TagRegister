package com.afrid.tag_register.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.adapter.LinenTypeAdapter;
import com.afrid.tag_register.bean.ServiceTLinen;
import com.afrid.tag_register.bean.json.BaseJsonResult;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.afrid.tag_register.ui.activity.WarehouseChoiceActivity;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;

/**
 * 功能：布草类型选择
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public class TagTypeChoiceFragment extends MyBaseFragment {

    private static final String TAG = "TagTypeChoiceFragment";

    @BindView(R.id.rv_linen_type)
    RecyclerView rv_linen_type;

    private APIMethodManager apiMethodManager;
    private LinenTypeAdapter linenAdapter;
    private MyApplication application;
    private Subscription subscription;
    private List<ServiceTLinen> linenList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_linen_choice;
    }


    @Override
    protected void beforeInit() {
        super.beforeInit();
        application = (MyApplication) getActivity().getApplication();
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    protected void initView() {
        rv_linen_type.setLayoutManager(new GridLayoutManager(getContext(), 3));
        linenAdapter = new LinenTypeAdapter(getContext());
        rv_linen_type.setAdapter(linenAdapter);
    }

    @Override
    protected void initListener() {

        rv_linen_type.addOnItemTouchListener(new OnRvItemClickListener(rv_linen_type) {
            @Override
            public void onItemClick(View itemView, int position) {
            /*    if (!SwingUManager.getInstance(getContext()).isConnected()) {
                    MyToast.showShort(getContext(), resourceUtils.getStr(R.string.swing_disconnect));
                    BTDeviceScanActivity.startAction(getActivity());
                    return;
                }*/
                application.setCheckedLinenTypeId(linenList.get(position).getLinenId());
                application.setCheckedLinenName(linenList.get(position).getLinenName());
                WarehouseChoiceActivity.startAction(getActivity());
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
        subscription = apiMethodManager.getAllLinen(new IRequestCallback<BaseJsonResult<List<ServiceTLinen>>>() {
            @Override
            public void onSuccess(BaseJsonResult<List<ServiceTLinen>> result) {
                int resultCode = result.getResultCode();
                if (resultCode == 200) {
                    linenList = result.getResultData();
                    linenAdapter.setDataBeanList(linenList);
                } else if (resultCode == 500) {
                    MyToast.show(getContext(), result.getMsg(), Toast.LENGTH_SHORT);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                hiddenLoadingDialog();
            }
        });

    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
