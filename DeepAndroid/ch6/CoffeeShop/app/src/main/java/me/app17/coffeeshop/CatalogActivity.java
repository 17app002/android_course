package me.app17.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CatalogActivity extends AppCompatActivity {

    private ListView itemList;

    public String[][] items = {
            {"每日精選咖啡", "摩卡可可碎片星冰樂", "芝麻抹茶那堤", "香檸蜜柚冷萃咖啡",
                    "金桔百香鮮果茶", "香檸雙果紅茶汽滋樂",
                    "兒童熱可可"},
            {"地瓜燕麥奶吐司帕里尼", "法式千層薄餅", "焙茶巧克力塔", "蔬食肉排三明治",
                    "燕麥奶生吐司", "6吋經典起司蛋糕"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        itemList = findViewById(R.id.item_list);

        //取得選單
        int catalog = getIntent().getExtras().getInt("catalog");

        //設定適配器
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                items[catalog]);

        itemList.setAdapter(arrayAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CatalogActivity.this, adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}