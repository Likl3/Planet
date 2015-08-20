package tabpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.planet.likunlang.planet.TaskDetailsPage;
import com.planet.likunlang.planet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

import http.HttpGetTask;

public class TabPersonPage extends Activity {

    private List<Map<String, Object>> target_list = new ArrayList<Map<String, Object>>();
    private int image;
    private String type;
    private String proj_name;
    private String content;
    private String start_time;
    private String end_time;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                Bundle bundle = msg.getData();
                System.out.println(bundle.getString("type"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_person_page);



        addItem(R.mipmap.person_on, "public",
                "项目名称", "任务内容，从前往后显示，超出部分省略号表示，参考微信",
                "8月9日", "8月10日");
        addItem(R.mipmap.person_on, "private", "planet", "安卓app开发","8月17日", "今天18:00");
    }



    public void addItem(int image, String type, String proj_name,
                        String content, String start_time, String end_time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", image);
        map.put("type", type);
        map.put("proj_name", proj_name);
        map.put("content", content);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        target_list.add(map);
        setAdapterAndListenr(target_list, image, type, proj_name, content, start_time, end_time);
    }

    public void setAdapterAndListenr(final List<Map<String, Object>> mlist, final int image,
                                     final String type, final String proj_name, final String content,
                                     final String start_time, final String end_time) {

        final SimpleAdapter listItemAdapter = new SimpleAdapter(
                TabPersonPage.this, mlist, R.layout.tab_person_page_list_item,
                new String[]{"image", "proj_name", "content", "end_time"},
                new int[]{R.id.tab_person_page_list_item_colorMark,
                        R.id.tab_person_page_list_item_proj_name,
                        R.id.tab_person_page_list_item_content,
                        R.id.tab_person_page_list_item_time});

        final ListView list = (ListView) findViewById(R.id.personal_target_list);
        list.setAdapter(listItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                @SuppressWarnings("unchecked")
                HashMap<String, Object> map = (HashMap<String, Object>) list
                        .getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("type", map.get("type").toString());
                bundle.putString("proj_name", map.get("proj_name").toString());
                bundle.putString("content", map.get("content").toString());
                bundle.putString("start_time", map.get("start_time").toString());
                bundle.putString("end_time", map.get("end_time").toString());
//                Intent intent = new Intent(TabPersonPage.this,
//                        TaskDetailsPage.class);
//                intent.putExtras(bundle);
//                startActivity(intent);

                Thread t = new Thread(getListThread);
                t.start();
            }
        });


//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                @SuppressWarnings("unchecked")
//                HashMap<String, Object> map = (HashMap<String, Object>) list
//                        .getItemAtPosition(arg2);

//                Bundle bundle = new Bundle();
//                // bundle.putInt("image", image);
//                bundle.putString("time", map.get("time").toString());
//                bundle.putString("person", map.get("userID").toString());
//                bundle.putBoolean("system", system);
//                bundle.putString("subject", map.get("subject").toString());
//                bundle.putString("body", map.get("content").toString());
//                Intent intent = new Intent(MessagePage.this,
//                        Message_Detail.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//
//            }
//        });

    }

    Runnable getListThread  = new Runnable() {
        @Override
        public void run() {
            HttpGetTask h = new HttpGetTask();

            String sr = h.sendPost("http://chuangyouji.sinaapp.com/get_tasks_by_uid", "uid=1").toString();
            System.out.println(sr);
            Message msg = new Message();
            msg.what = 0;
            Bundle bundle = new Bundle();
            bundle.putString("type", sr);
//            bundle.putString("content", "aaaaaaa");
            msg.setData(bundle);
            handler.sendMessage(msg);

        }
    };
}
