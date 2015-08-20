package com.planet.likunlang.planet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.logging.Handler;

import tabpage.TabPersonPage;


public class TaskDetailsPage extends Activity implements View.OnClickListener {
    private String type;
    private String proj_name;
    private String content;
    private String start_time;
    private String end_time;

    private EditText edt_content;
    private TextView txt_start_time, txt_end_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details_page);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.task_detail_title_bar_style);

        GetIntent();
        //本页面所有按钮在此统一声明，下面统一监听
        ((ImageButton)findViewById(R.id.btn_back)).setOnClickListener(this);
        ((ImageButton)findViewById(R.id.btn_delete)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_ok)).setOnClickListener(this);

    }
    //信息详情页标题栏按钮监听：返回与删除
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_delete:
                //TODO 向服务器发送删除命令
                Intent intent = new Intent(TaskDetailsPage.this, TabPersonPage.class);
                finish();
                break;
            case R.id.btn_ok:
                //TODO 保存数据

    }
}


    //从TabPersonPage获取单项任务详细信息
    @SuppressLint("NewApi")
    private void GetIntent()  {
        Bundle bundle = this.getIntent().getExtras();
        //iconURL = bundle.getString("URL");
        type = bundle.getString("type", "");
        proj_name = bundle.getString("proj_name",  "");
        content = bundle.getString("content", "");
        start_time = bundle.getString("start_time", "");
        end_time = bundle.getString("end_time", "");

        edt_content = (EditText)findViewById(R.id.edit_text_target_details);
        txt_start_time = (TextView)findViewById(R.id.edit_text_date);
        txt_end_time = (TextView)findViewById(R.id.edit_text_time);

        if(!type.equals("") && !content.equals("")) {
            edt_content.setText(content);
            txt_start_time.setText(start_time);
            txt_end_time.setText(end_time);

            edt_content.setFocusable(false);
//            edt_content.isFocusableInTouchMode();

            if(type.equals("public")) {
                edt_content.setEnabled(false);
            }
        }
    }

}
