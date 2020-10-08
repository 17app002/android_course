package me.app17.coffeeshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/***
 * CatalogActivity
 */
public class ItemActivity extends AppCompatActivity {
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Item item = (Item) getIntent().getSerializableExtra("item");
        FragmentManager fragmentManager = getSupportFragmentManager();
        //切換至CatalogFragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        itemFragment = new ItemFragment();
        fragmentTransaction.add(R.id.item_fragment, itemFragment, "item_fragment");

        //傳遞選擇的item
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        itemFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}
