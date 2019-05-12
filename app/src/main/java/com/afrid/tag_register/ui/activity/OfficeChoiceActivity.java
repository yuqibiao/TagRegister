package com.afrid.tag_register.ui.activity;

import android.view.View;

import com.afrid.tag_register.R;
import com.afrid.tag_register.ui.activity.MyBaseActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2018/1/12
 */

public class OfficeChoiceActivity extends MyBaseActivity{
    @Override
    public int getLayoutId() {
        return R.layout.activity_office_choice;
    }

    @Override
    public void beforeInit() {
        super.beforeInit();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }
    public void back(View view) {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void close(String message) {
      if("close".equals(message)){
          finish();
      }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
