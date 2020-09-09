package me.app17.chapter4_1;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private String[] mapKey = {"title", "info", "image"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item_lv_layout,
                mapKey, new int[]{R.id.title_tv, R.id.info_tv, R.id.icon_iv});

        this.setListAdapter(simpleAdapter);

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
