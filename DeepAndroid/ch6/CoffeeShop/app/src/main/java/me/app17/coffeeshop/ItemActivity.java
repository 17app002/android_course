package me.app17.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_1);

        Item item = (Item) getIntent().getSerializableExtra("item");
        ImageView itemImage = findViewById(R.id.item_image);
        TextView titleText = findViewById(R.id.title_text);
        TextView priceText = findViewById(R.id.price_text);
        TextView infoText = findViewById(R.id.info_text);
        TextView engText = findViewById(R.id.eng_title_text);

        itemImage.setImageResource(item.getResId());
        engText.setText(item.getSubTitle());
        titleText.setText(item.getTitle());
        priceText.setText("價格:" + String.valueOf(item.getPrice()));
        infoText.setText(item.getInfo());
        infoText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.side));

    }
}