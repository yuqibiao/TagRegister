package com.afrid.tag_register.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.adapter.OfficeAdapter;
import com.afrid.tag_register.bean.json.return_data.GetHospitalOfficeReturn;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.afrid.tag_register.ui.activity.WashFactoryChoiceActivity;
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

public class OfficeChoiceFragment extends MyBaseFragment{

    @BindView(R.id.rv_warehouse)
    RecyclerView rv_office;
    private OfficeAdapter officeAdapter;
    private APIMethodManager apiMethodManager;
    private MyApplication application;
    private int checkHospitalId;
    private List<GetHospitalOfficeReturn.ResultDataBean> resultData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_office_choice;
    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        application = (MyApplication) getActivity().getApplication();
        apiMethodManager = APIMethodManager.getInstance();
        checkHospitalId = application.getCheckHospitalId();
    }

    @Override
    protected void initView() {
        rv_office.setLayoutManager(new GridLayoutManager(getContext(), 3));
        officeAdapter = new OfficeAdapter(getContext());
        rv_office.setAdapter(officeAdapter);
    }

    @Override
    protected void initListener() {
        rv_office.addOnItemTouchListener(new OnRvItemClickListener(rv_office) {
            @Override
            public void onItemClick(View itemView, int position) {
                GetHospitalOfficeReturn.ResultDataBean resultDataBean = resultData.get(position);
                application.setCheckedOfficeId(resultDataBean.getOfficeId());
                application.setCheckedOfficeName(resultDataBean.getOfficeName());
               Intent intent = new Intent(getActivity() , WashFactoryChoiceActivity.class);
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
        apiMethodManager.getHospitalOffice(checkHospitalId, new IRequestCallback<GetHospitalOfficeReturn>() {
            @Override
            public void onSuccess(GetHospitalOfficeReturn result) {
                int resultCode = result.getResultCode();
                String msg = result.getMsg();
                if (resultCode==200){
                    resultData = result.getResultData();
                    officeAdapter.setDataBeanList(resultData);
                }else{
                    MyToast.showShort(getContext(), msg);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                hiddenLoadingDialog();
            }
        });
    }
}
