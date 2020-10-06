package me.app17.starboxfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemDetailFragment extends Fragment {
    private Item item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //設定主要呈現的view
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view=getView();

        ImageView itemImage = view.findViewById(R.id.item_image);
        TextView titleText = view.findViewById(R.id.title_text);
        TextView priceText = view.findViewById(R.id.price_text);
        TextView infoText = view.findViewById(R.id.info_text);
        TextView engText = view.findViewById(R.id.eng_title_text);

        itemImage.setImageResource(item.getResId());
        engText.setText(item.getSubTitle());
        titleText.setText(item.getTitle());
        priceText.setText("價格:" + String.valueOf(item.getPrice()));
        infoText.setText(item.getInfo());
        infoText.startAnimation(AnimationUtils.loadAnimation(getLayoutInflater().getContext(), R.anim.side));
    }

    public void setItem(Item item){
        this.item=item;
    }
}