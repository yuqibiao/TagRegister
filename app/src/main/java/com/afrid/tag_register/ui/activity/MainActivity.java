package com.afrid.tag_register.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afrid.tag_register.MyApplication;
import com.afrid.tag_register.R;
import com.afrid.tag_register.global.Constant;
import com.afrid.tag_register.net.APIMethodManager;
import com.afrid.tag_register.ui.TestActivity;
import com.afrid.tag_register.ui.fragment.SupplierChoiceFragment;
import com.afrid.tag_register.ui.fragment.TagTypeChoiceFragment;
import com.afrid.swingu.utils.SwingUManager;
import com.afrid.tag_register.ui.fragment.ZhengZhouTagTypeChoiceFragment;
import com.yyyu.baselibrary.utils.ActivityHolder;
import com.yyyu.baselibrary.utils.MySPUtils;
import com.yyyu.baselibrary.utils.MyToast;

import butterknife.BindView;

/**
 * 功能：主界面
 *
 * @author yu
 * @version 1.0
 * @date 2017/9/12
 */

public class MainActivity extends MyBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    TextView tv_username;

    private APIMethodManager apiMethodManager;
    private MyApplication application;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInit() {
        super.beforeInit();
        application = (MyApplication)getApplication();
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    protected void initView() {
        navView.inflateHeaderView(R.layout.nav_header_main);
        tv_username = (TextView) navView.getHeaderView(0).findViewById(R.id.tv_username);
        MyApplication application = (MyApplication) getApplication();
        tv_username.setText(application.getUser_name());
        switch (client){//--------------------TODO
            case COMMON:
            case HONG_KONG:
                replaceFrg(R.id.fl_content, new TagTypeChoiceFragment());
                break;
            case ZHENG_ZHOU:
                replaceFrg(R.id.fl_content, new ZhengZhouTagTypeChoiceFragment());
                break;
        }
        navView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {//首页
            switch (client){//--------------------TODO
                case COMMON:
                case HONG_KONG:
                    replaceFrg(R.id.fl_content, new TagTypeChoiceFragment());
                    break;
                case ZHENG_ZHOU:
                    replaceFrg(R.id.fl_content, new SupplierChoiceFragment());
                    break;
            }
        } else if (itemId == R.id.nav_print_test) {
            Intent intent = new Intent(this , TestActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_bt) {
            BTDeviceScanActivity.startAction(this);
        } else if (itemId == R.id.nav_exit) {//退出
            MySPUtils.remove(this, Constant.USER_INFO);
            ActivityHolder.finishedAll();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void toggleNav(View view){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            drawer.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 跳转界面
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        SwingUManager.getInstance(this).destoryReader();
        super.onDestroy();
    }

    long currentTime;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - currentTime > 2 * 1000) {
                MyToast.showShort(this, resourceUtils.getStr(R.string.main_exit_tip));
            } else {
                if (SwingUManager.getInstance(this).isConnected()) {
                    SwingUManager.getInstance(this).stopReader();
                }
                //moveTaskToBack(false);
                super.onBackPressed();
            }
            currentTime = System.currentTimeMillis();
        }

    }
}
