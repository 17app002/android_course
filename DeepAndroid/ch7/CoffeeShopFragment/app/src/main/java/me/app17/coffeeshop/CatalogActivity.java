package me.app17.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/***
 * CatalogActivity
 */
public class CatalogActivity extends FullScreenActivity implements CatalogClickListener {

    private CatalogFragment catalogFragment;
    private FragmentManager fragmentManager;
    boolean dualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        fragmentManager = getSupportFragmentManager();

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


        //檢查是否有item_fragment(dualPane)
        View itemFrame = findViewById(R.id.item_fragment);
        dualPane = itemFrame != null;
    }

    @Override
    public void itemClicked(Item item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();

        //雙視窗
        if (dualPane) {

            fragmentManager = getSupportFragmentManager();
            //切換至CatalogFragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ItemFragment itemFragment = new ItemFragment();
            fragmentTransaction.replace(R.id.item_fragment, itemFragment, "item_fragment");
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            //傳遞選擇的item
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", item);
            itemFragment.setArguments(bundle);
            fragmentTransaction.commit();

            return;
        }

        //Single Pane
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
