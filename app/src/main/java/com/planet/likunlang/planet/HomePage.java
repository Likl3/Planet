package com.planet.likunlang.planet;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import tabpage.TabPersonPage;
import tabpage.TabProjectsPage;
import tabpage.TabSquarePage;
import tabpage.TabTeamsPage;

public class HomePage extends TabActivity implements CompoundButton.OnCheckedChangeListener {

    private Intent intent1, intent2, intent3, intent4;
    private TabHost tabHost;
    private ImageButton btn_add, btn_other;
    private PopupWindow myPopupWindow;
    private Button btn_add_task, btn_add_proj;
    View customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //重定义标题栏
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.home_page_title_bar_style);

//        main_tab_new_message=(TextView) findViewById(R.id.main_tab_new_message);
//        main_tab_new_message.setVisibility(View.VISIBLE);
//        main_tab_new_message.setText("10");

        //底部四个导航标签的intent
        intent1 = new Intent(this, TabPersonPage.class);
        intent2 = new Intent(this, TabProjectsPage.class);
        intent3 = new Intent(this, TabTeamsPage.class);
        intent4 = new Intent(this, TabSquarePage.class);
        setupIntent();

        //四个导航标签按键
        ((RadioButton) findViewById(R.id.person))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.projects))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.teams))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.square))
                .setOnCheckedChangeListener(this);

        /////////////////////////

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        customView = inflater.inflate(R.layout.popview_items, null);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        myPopupWindow = new PopupWindow(customView, 250, 280);
        myPopupWindow.setFocusable(true);
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        btn_add_task = (Button) customView.findViewById(R.id.btn_add_task);
        btn_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "");
                bundle.putString("proj_name", "");
                bundle.putString("content", "");
                bundle.putString("start_time", "");
                bundle.putString("end_time", "");

                Intent intent = new Intent(HomePage.this,
                        TaskDetailsPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        /////////////////////////
        btn_add = (ImageButton) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopupWindow.showAsDropDown(v, -10, 0);
            }
        });
    }

    public void initialPopupWindow() {
        View customView = getLayoutInflater().inflate(R.layout.popview_items,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        myPopupWindow = new PopupWindow(customView, 250, 280);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        myPopupWindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        myPopupWindow.setFocusable(true);
        myPopupWindow.setOutsideTouchable(true);
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (myPopupWindow != null && myPopupWindow.isShowing()) {
                    myPopupWindow.dismiss();
                    myPopupWindow = null;
                }
                return false;
            }
        });
    }

    // 底部四个导航标签的监听
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            switch (buttonView.getId()) {
                case R.id.person:
                    this.tabHost.setCurrentTabByTag("A_TAB");
                    ((RadioButton) findViewById(R.id.person))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.person_on), null, null);
                    ((RadioButton) findViewById(R.id.projects))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.projects), null, null);
                    ((RadioButton) findViewById(R.id.teams))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    ((RadioButton) findViewById(R.id.square))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    break;
                case R.id.projects:
                    this.tabHost.setCurrentTabByTag("B_TAB");
                    ((RadioButton) findViewById(R.id.projects))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.projects_on), null, null);
                    ((RadioButton) findViewById(R.id.person))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.person), null, null);
                    ((RadioButton) findViewById(R.id.teams))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    ((RadioButton) findViewById(R.id.square))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    break;
                case R.id.teams:
                    this.tabHost.setCurrentTabByTag("C_TAB");
                    ((RadioButton) findViewById(R.id.teams))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams_on), null, null);
                    ((RadioButton) findViewById(R.id.person))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.person), null, null);
                    ((RadioButton) findViewById(R.id.projects))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.projects), null, null);
                    ((RadioButton) findViewById(R.id.square))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    break;
                case R.id.square:
                    this.tabHost.setCurrentTabByTag("D_TAB");
                    ((RadioButton) findViewById(R.id.square))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams_on), null, null);
                    ((RadioButton) findViewById(R.id.person))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.person), null, null);
                    ((RadioButton) findViewById(R.id.projects))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.projects), null, null);
                    ((RadioButton) findViewById(R.id.teams))
                            .setCompoundDrawablesWithIntrinsicBounds(null, getResources()
                                    .getDrawable(R.mipmap.teams), null, null);
                    break;
            }
        }
    }

    // TabHost添加底部导航标签
    private void setupIntent() {
        this.tabHost =  getTabHost();
        TabHost localTabHost = this.tabHost;

        localTabHost.addTab(buildTabSpec("A_TAB", R.string.person,
                R.mipmap.person, this.intent1));

        localTabHost.addTab(buildTabSpec("B_TAB", R.string.projects,
                R.mipmap.projects, this.intent2));

        localTabHost.addTab(buildTabSpec("C_TAB", R.string.teams,
                R.mipmap.teams, this.intent3));

        localTabHost.addTab(buildTabSpec("D_TAB", R.string.square,
                R.mipmap.teams, this.intent4));

        localTabHost.setCurrentTab(1);
    }
    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
                                         final Intent content) {
        return this.tabHost.newTabSpec(tag).setIndicator(getString(resLabel),
                getResources().getDrawable(resIcon)).setContent(content);
    }


}


