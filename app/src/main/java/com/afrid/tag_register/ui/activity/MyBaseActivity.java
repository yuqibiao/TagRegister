package com.afrid.tag_register.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yyyu.baselibrary.template.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public abstract class MyBaseActivity extends BaseActivity{

    private Unbinder mUnbind;
    //---客户类型（不同的客户加载不同的界面）
    public static  Client client ;
    public enum Client{
        COMMON,//通用类型
        ZHENG_ZHOU,//郑州客户(界面不同、稍有改动)
        HONG_KONG,//香港客户（登录时密码不加密处理）
    }

    @Override
    public void beforeInit() {
        super.beforeInit();
        mUnbind = ButterKnife.bind(this);
        //-------------------设置客户类型
        client = Client.COMMON;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbind!=null){
            mUnbind.unbind();
        }
    }

    public void replaceFrg(int resId , Fragment fragment){
      replaceFrg(resId , fragment , false);
    }

    public void replaceFrg(int resId , Fragment fragment , boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(resId, fragment);
        if (addToBackStack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }

}
