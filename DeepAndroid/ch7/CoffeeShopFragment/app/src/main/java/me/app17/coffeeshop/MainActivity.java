package me.app17.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FullScreenActivity implements View.OnClickListener, CatalogClickListener {
    private ImageButton coffeeBtn;
    private ImageButton dessertBtn;
    private ImageButton storeBtn;


    private FragmentManager fragmentManager;

    private CatalogFragment catalogFragment;
    private ItemFragment itemFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        findViews();
    }

    public void findViews() {
        coffeeBtn = findViewById(R.id.coffee_btn);
        dessertBtn = findViewById(R.id.dessert_btn);
        storeBtn = findViewById(R.id.store_btn);

        coffeeBtn.setOnClickListener(this);
        dessertBtn.setOnClickListener(this);
        storeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int catalogId = 0;
        switch (view.getId()) {
            case R.id.coffee_btn:
                catalogId = 0;
                break;
            case R.id.dessert_btn:
                catalogId = 1;
                break;
            case R.id.store_btn:
                catalogId = 2;
                break;
        }

        if (catalogId >= 2) {
            Toast.makeText(this, "功能未完成....",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //切換至CatalogFragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //移除舊的catalogFragment
        if (catalogFragment != null) {
            fragmentTransaction.remove(catalogFragment);
            catalogFragment = null;
        }

        catalogFragment = new CatalogFragment();
        fragmentTransaction.add(R.id.container_fragment, catalogFragment, "CATALOG");


        //傳遞選擇的catalog
        Bundle bundle = new Bundle();
        bundle.putInt("CATALOG_ID", catalogId);

        catalogFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void itemClicked(Item item) {
        System.out.println(item);
        //切換至CatalogFragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //移除舊的catalogFragment
        if (itemFragment != null) {
            fragmentTransaction.remove(catalogFragment);
            itemFragment = null;
        }

        itemFragment = new ItemFragment();
        fragmentTransaction.add(R.id.container_fragment, itemFragment, "CATALOG");


        //傳遞選擇的item
        Bundle bundle = new Bundle();
        bundle.putSerializable("ITEM", item);

        itemFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}