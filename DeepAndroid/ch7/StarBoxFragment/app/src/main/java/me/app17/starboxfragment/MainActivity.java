package me.app17.starboxfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemDetailFragment itemDetailFragment = (ItemDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        itemDetailFragment.setItem(new Item(R.drawable.dessert_0, "地瓜燕麥奶吐司帕里尼",
                "Sweet Potato Oat Milk Toast Panini (with Egg & Nuts)", 175, "以燕麥奶生吐司夾入混和著多樣堅果的風味地瓜泥，如:高蛋白質紅藜麥、葵花子、核桃等，搭配煎蛋片、美生菜與牛蕃茄，多層次的健康美味。"));


        CatalogFragment catalogFragment=(CatalogFragment)getSupportFragmentManager().findFragmentById(R.id.catalog_fragment);

        catalogFragment.setCatalog(1);

    }
}