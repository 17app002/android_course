package me.app17.coffeeshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemFragment extends Fragment {
    private Item item;
    private ImageView itemImage;
    private TextView titleText;
    private TextView priceText;
    private TextView infoText;
    private TextView engText;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            item = (Item) getArguments().getSerializable("item");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.item_fragment, container, false);

        itemImage = root.findViewById(R.id.item_image);
        titleText = root.findViewById(R.id.title_text);
        priceText = root.findViewById(R.id.price_text);
        infoText = root.findViewById(R.id.info_text);
        engText = root.findViewById(R.id.eng_title_text);

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (item != null) {
            itemImage.setImageResource(item.getResId());
            engText.setText(item.getSubTitle());
            titleText.setText(item.getTitle());
            priceText.setText("價格:" + String.valueOf(item.getPrice()));
            infoText.setText(item.getInfo());
            infoText.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.side));
        }
    }

}