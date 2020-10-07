package me.app17.coffeeshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CatalogFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView itemLv;
    private CatalogClickListener catalogClickListener;
    private int catalogId;
    private List<Item> itemList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //取得按鍵偵測參考
        catalogClickListener = (CatalogClickListener) context;
        //取得傳入值
        if (getArguments() != null) {
            catalogId = getArguments().getInt("CATALOG_ID");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_catalog, container, false);
        itemLv = root.findViewById(R.id.item_list);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        //依照選項取得商品資料
        itemList = new Catalog(catalogId, "").getItemList();
        List<String> titles = new ArrayList<>();
        //整合商品名稱
        for (Item item : itemList) {
            titles.add(item.getTitle());
        }

        //設定適配器
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                titles);

        itemLv.setAdapter(arrayAdapter);
        itemLv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (catalogClickListener != null) {
            catalogClickListener.itemClicked(itemList.get(i));
        }

        Toast.makeText(getContext(), adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();
    }
}