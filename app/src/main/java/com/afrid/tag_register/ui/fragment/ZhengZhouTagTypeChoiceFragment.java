package com.afrid.tag_register.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.ui.activity.OfficeChoiceActivity;
import com.afrid.tag_register.adapter.ZhengZhouLinenTypeAdapter;
import com.afrid.tag_register.bean.json.return_data.GetHospitalLinenReturn;
import com.afrid.tag_register.bean.json.return_data.GetHospitalReturn;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.net.IRequestCallback;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;

/**
 * 功能：库房选择
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public class ZhengZhouTagTypeChoiceFragment extends MyBaseFragment {

    private static final String TAG = "ZhengZhouTagTypeChoiceFragment";

    @BindView(R.id.rv_linen_type)
    RecyclerView rv_linen_type;
    @BindView(R.id.sp_hos_choice)
    Spinner sp_hos_choice;

    private APIMethodManager apiMethodManager;
    private ZhengZhouLinenTypeAdapter linenTypeAdapter;
    private MyApplication application;
    private Subscription subscription;
    private List<GetHospitalReturn.ResultDataBean> hospitalList;
    private List<GetHospitalLinenReturn.ResultDataBean> linenList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_linen_choice_zhengzhou;
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
        linenTypeAdapter = new ZhengZhouLinenTypeAdapter(getContext());
        rv_linen_type.setAdapter(linenTypeAdapter);
    }

    @Override
    protected void initListener() {

        sp_hos_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                application.setCheckHospitalId(hospitalList.get(i).getHospitalId());
                application.setCheckHospitalName(hospitalList.get(i).getHospitalName());
                showLoadDialog("加载布草类型...");

                apiMethodManager.getLinenByHospitalId(application.getCheckHospitalId(), new IRequestCallback<GetHospitalLinenReturn>() {
                    @Override
                    public void onSuccess(GetHospitalLinenReturn result) {
                        int resultCode = result.getResultCode();
                        if (resultCode == 200) {
                            linenList = result.getResultData();
                            linenTypeAdapter.setDataBeanList(linenList);
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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rv_linen_type.addOnItemTouchListener(new OnRvItemClickListener(rv_linen_type) {
            @Override
            public void onItemClick(View itemView, int position) {

                application.setCheckedLinenTypeId(linenList.get(position).getLinenId());
                application.setCheckedLinenName(linenList.get(position).getLinenName());
                //跳转科室选择界面
                Intent intent = new Intent(getActivity() , OfficeChoiceActivity.class);
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
        super.initData();

        subscription = apiMethodManager.getHospitalByUserId(application.getUser_id(), new IRequestCallback<GetHospitalReturn>() {
            @Override
            public void onSuccess(GetHospitalReturn result) {
                MyLog.e(TAG, "result===" + result.getMsg());
                int resultCode = result.getResultCode();
                if (resultCode == 200) {
                    hospitalList = result.getResultData();
                    List<String> hospitalNameList = new ArrayList<>();
                    for (GetHospitalReturn.ResultDataBean bean :hospitalList){
                        hospitalNameList.add(bean.getHospitalName());
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item , hospitalNameList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_hos_choice.setAdapter(adapter);
                    if (linenList !=null && linenList.size()>0){
                        sp_hos_choice.setSelection(0);
                    }
                } else if (resultCode == 500) {
                    MyToast.show(getContext(), result.getMsg(), Toast.LENGTH_SHORT);
                }
                hiddenLoadingDialog();
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e("异常=="+throwable.getMessage());
                hiddenLoadingDialog();
                MyToast.show(getContext(), resourceUtils.getStr(R.string.net_error), Toast.LENGTH_SHORT);
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
