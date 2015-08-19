package tabpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.planet.likunlang.planet.TaskDetailsPage;
import com.planet.likunlang.planet.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TabPersonPage extends Activity {

    private List<Map<String, Object>> target_list = new ArrayList<Map<String, Object>>();
    private int image;
    private String proj_name;
    private String content;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_person_page);

        addItem(R.mipmap.person_on, "项目名称", "任务内容，从前往后显示，超出部分省略号表示，参考微信", "8月10日");
        addItem(R.mipmap.person_on, "planet", "安卓app开发","今天18:00");
    }


    public void addItem(int image, String proj_name, String content, String time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", image);
        map.put("proj_name", proj_name);
        map.put("content", content);
        map.put("time", time);
        target_list.add(map);
        setAdapterAndListenr(target_list, image, proj_name, content, time);
    }

    public void setAdapterAndListenr(final List<Map<String, Object>> mlist, final int image,
                                     final String proj_name, final String content, final String time) {

        final SimpleAdapter listItemAdapter = new SimpleAdapter(
                TabPersonPage.this, mlist, R.layout.tab_person_page_list_item,
                new String[]{"image", "proj_name", "content", "time"},
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
                bundle.putString("proj_name", map.get("proj_name").toString());
                bundle.putString("content", map.get("content").toString());
                bundle.putString("time", map.get("time").toString());
                Intent intent = new Intent(TabPersonPage.this,
                        TaskDetailsPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
}
