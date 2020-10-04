package me.app17.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private ListView itemLv;
//
//    public String[][] items = {
//            {"每日精選咖啡", "摩卡可可碎片星冰樂", "芝麻抹茶那堤", "香檸蜜柚冷萃咖啡",
//                    "金桔百香鮮果茶", "香檸雙果紅茶汽滋樂",
//                    "兒童熱可可"},
//            {"地瓜燕麥奶吐司帕里尼", "法式千層薄餅", "焙茶巧克力塔", "蔬食肉排三明治",
//                    "燕麥奶生吐司", "6吋經典起司蛋糕"},
//    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        itemLv = findViewById(R.id.item_list);
        //取得選單選項
        int catalog = getIntent().getExtras().getInt("catalog");

        //依照選項取得商品資料
        final List<Item> itemList = getItemData(catalog);
        List<String> titles = new ArrayList<>();

        //整合商品名稱
        for (Item item : itemList) {
            titles.add(item.getTitle());
        }

        //設定適配器
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                titles);

        itemLv.setAdapter(arrayAdapter);

        itemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(CatalogActivity.this, adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();

                Item item = itemList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                Intent intent = new Intent(CatalogActivity.this, ItemActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    /***
     * 讀取商品列表
     * @param catalog
     */
    public List<Item> getItemData(int catalog) {

        List<Item> itemList = new ArrayList<>();
        //coffee
        if (catalog == 0) {
            itemList.add(new Item(R.drawable.coffee_0, "每日精選咖啡", 75,
                    "精心為您挑選來自不同產區的每日精選咖啡，帶給您多元的咖啡風味。"));

            itemList.add(new Item(R.drawable.coffee_1, "芝麻抹茶那堤", 145,
                    "台灣在地開發飲品，秋季再次回歸，芝麻熟悉溫潤的香氣與抹茶優雅的滋味結合，" +
                            "迎合秋季淡雅風味的飲品，在品嘗間體會口中風味多層次的變化，芝麻與抹茶交織的美味。"));

            itemList.add(new Item(R.drawable.coffee_2, "摩卡可可碎片星冰樂", 145,
                    "同樣為暢銷口味，結合星巴克咖啡、鮮奶、摩卡醬及可可碎片，以鮮奶油與摩卡醬裝飾，多層次口感及濃郁巧克力風味，獨具魅力。"));

            itemList.add(new Item(R.drawable.coffee_3, "香檸蜜柚冷萃咖啡", 135,
                    "檸檬清新的香氣，加入了紅柚醬與蜂蜜調和，更帶出冷萃咖啡本身的巧克力甘甜與柑橘的風味調性，讓柑橘類系風味完美的與咖啡結合，創造出平衡美妙的滋味。"));

            itemList.add(new Item(R.drawable.coffee_4, "香檸雙果紅茶汽滋樂", 125,
                    "芒果富郁的果香、與清香的檸檬果汁和帶有淡淡花香的阿薩姆紅茶結合，打入氣泡後，一層層美妙的風味隨著氣泡冉冉上升，優雅卻暢快的滋味瞬間沁涼了炎炎的暑氣。\n" +
                            "限定門市銷售，請至星巴克網站門市專區-門市查詢\n" +
                            "實際供貨情況依各門市狀況會有不同，敬請見諒。"));

            itemList.add(new Item(R.drawable.coffee_5, "兒童熱可可", 95,
                    "濃郁香甜的巧克力風味，是小朋友的最愛。"));

            itemList.add(new Item(R.drawable.coffee_6, "金桔百香鮮果茶", 70,
                    "使用HPP技術，保留鮮果新鮮滋味\n" +
                            "內含清香金桔、酸甜百香果，與淡雅甘甜的茉莉綠茶，交織出迷人芳香、酸中帶甜的美好滋味，滿滿的果粒更是咀嚼系不可錯過的選擇"));


        } else if (catalog == 1) {
            itemList.add(new Item(R.drawable.dessert_0, "地瓜燕麥奶吐司帕里尼", 175, "以燕麥奶生吐司夾入混和著多樣堅果的風味地瓜泥，如:高蛋白質紅藜麥、葵花子、核桃等，搭配煎蛋片、美生菜與牛蕃茄，多層次的健康美味。"));

            itemList.add(new Item(R.drawable.dessert_1, "蔬食肉排三明治", 235, "將蔬食肉(Beyond Meat)加入有咬感的菇類、花椰菜碎料等食材，手工捏製成肉排，夾在長型軟麵包中，加入新鮮牛番茄使整體更加清爽，搭配起司、洋菇片、拌炒過的洋蔥絲增添層次，抹上擬煙燻培根風味美乃茲，提增香氣。"));

            itemList.add(new Item(R.drawable.dessert_2, "焙茶巧克力塔", 230,
                    "於巧克力塔皮中夾入一層擂茶海綿蛋糕，灌入焙茶奶餡，上面淋上薄薄的巧克力淋面，整體散發茶香，並有巧克力尾韻，擁有美妙的層次風味變化。"));

            itemList.add(new Item(R.drawable.dessert_3, "燕麥奶生吐司", 60,
                    "今年最夯的吐司非生吐司莫屬！麵糰經18小時低溫發酵，使用優質原料日本皇后牌麵粉及鐵塔奶油，再加入OATLY燕麥奶增添柔軟度及豐富膳食纖維，越嚼越香而且單吃就很好吃！"));

            itemList.add(new Item(R.drawable.dessert_4, "法式千層薄餅", 750,
                    "一片片輕薄Q彈的法式餅皮，搭配香氣迷人的香草卡士達餡；透過主廚輕柔手法，細心呵護的將每一層餅皮中裹上滑順的香草卡士達餡，交疊成美好的法式千層薄餅。"));

            itemList.add(new Item(R.drawable.dessert_5, "6\"Classic Cheese Cake", 850,
                    "廣受顧客喜愛的經典起司蛋糕，再次推出六吋大小搶攻蛋糕市場。金黃耀眼的經典濃郁起司香氣四溢，入口即化，絲滑入心。"));

        }

        return itemList;
    }

}