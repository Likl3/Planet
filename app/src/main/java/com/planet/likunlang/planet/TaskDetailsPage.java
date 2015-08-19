package com.planet.likunlang.planet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.logging.Handler;

import tabpage.TabPersonPage;


public class TaskDetailsPage extends Activity implements View.OnClickListener {
    private String proj_name;
    private String content;
    private String time;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details_page);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.task_detail_title_bar_style);

        GetIntent();
//        text = (TextView)findViewById(R.id.details_text);
//        text.setText(proj_name + "\n" + content + "\n" + time);

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
        proj_name = bundle.getString("proj_name",  "无法显示");
        content = bundle.getString("content", "无");
        time = bundle.getString("time", "无");
    }

}
