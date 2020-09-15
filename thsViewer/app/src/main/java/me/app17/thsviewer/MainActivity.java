package me.app17.thsviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity  implements Runnable{

    private ListView itemLv;
    private Button updateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemLv=findViewById(R.id.item_lv);
        updateBtn=findViewById(R.id.update_btn);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new THSViewer()).start();
                new Thread(MainActivity.this).start();
            }
        });
    }






    @Override
    public void run() {
        while(true){
            if(THSViewer.status==-1){
                Log.i("info","連結失敗!");
                break;
            }

            if(THSViewer.status==1){
                Log.i("info","資料抓取成功!");

                String[] columns={"no","date","start_loc","end_loc"};

                List<Map<String,Object>> mapList=new ArrayList<>();

                for(ThsData data:THSViewer.thsDataList){
                    Map<String,Object> map=new HashMap<>();
                    map.put("no",data.no);
                    map.put("date",data.date);
                    map.put("start_loc",data.startLoc);
                    map.put("end_loc",data.endLoc);

                    mapList.add(map);

                }

                final SimpleAdapter simpleAdapter=new SimpleAdapter(this,mapList,R.layout.ths_listview_layout,
                        columns,new int[]{R.id.thsno_tv,R.id.date_tv,R.id.start_tv,R.id.end_tv});

                itemLv.post(new Runnable() {
                    @Override
                    public void run() {
                        itemLv.setAdapter(simpleAdapter);
                    }
                });

                break;
            }


        }
    }
}


