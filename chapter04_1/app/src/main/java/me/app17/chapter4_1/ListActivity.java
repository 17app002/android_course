package me.app17.chapter4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private String[] mapKey = {"title", "info", "image"};
    private ListView itemLv;
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemLv = findViewById(R.id.item_lv);
        titleTv = findViewById(R.id.title_tv);


        titleTv.setText("你選擇的是?");


        itemLv.setAdapter(new SimpleAdapter(this, getData(), R.layout.item_lv_layout, mapKey, new int[]{
                R.id.title_tv, R.id.info_tv, R.id.icon_iv}));


        itemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String, Object> map = (HashMap<String, Object>) itemLv.getItemAtPosition(position);
                titleTv.setText(map.get("title").toString());

                Drawable drawable = getResources().getDrawable((int) map.get("image"));
                findViewById(R.id.icon_iv).setBackground(drawable);
            }
        });

    }

    private List<Map<String, Object>> getData() {

        String[] title = {"個人資訊", "收支情況", "日記本"};
        String[] info = {"ver 1.0", "66666", "2020/9/6"};
        int[] imageId = {R.drawable.icon1,
                R.drawable.icon2,
                R.drawable.icon3};

        List<Map<String, Object>> itemList = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put(mapKey[0], title[i]);
            map.put(mapKey[1], info[i]);
            map.put(mapKey[2], imageId[i]);

            itemList.add(map);

        }

        return itemList;
    }
}
