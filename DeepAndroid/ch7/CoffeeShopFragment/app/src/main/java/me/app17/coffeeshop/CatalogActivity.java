package me.app17.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/***
 * CatalogActivity
 */
public class CatalogActivity extends AppCompatActivity implements CatalogClickListener{

    private CatalogFragment catalogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        FragmentManager fragmentManager = getSupportFragmentManager();

        //取得選單選項
        int catalogId = getIntent().getExtras().getInt("catalog");
        //切換至CatalogFragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        catalogFragment = new CatalogFragment();
        fragmentTransaction.add(R.id.catalog_fragment, catalogFragment, "catalog_fragment");
        //傳遞選擇的catalog
        Bundle bundle = new Bundle();
        bundle.putInt("catalog", catalogId);
        catalogFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void itemClicked(Item item) {
        Toast.makeText(this, item.getTitle(),Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
