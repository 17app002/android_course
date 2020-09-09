package me.app17.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ListView featuresLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featuresLv = findViewById(R.id.features_lv);

        String[] data = {"個人資訊", "收支情況", "日記本"};

        Adapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        featuresLv.setAdapter((ArrayAdapter) adapter);

        //featuresLv.setOnItemClickListener(this);
        featuresLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), featuresLv.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });


//        featuresLv.setOnItemClickListener((parent,view,position,id)->{
//                   Toast.makeText(parent.getContext(), featuresLv.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//
//        });
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//    }
}
