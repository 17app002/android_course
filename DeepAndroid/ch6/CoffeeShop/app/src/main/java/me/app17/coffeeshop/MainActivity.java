package me.app17.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton coffeeBtn;
    private ImageButton dessertBtn;
    private ImageButton storeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fullScreen();

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

    public void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    @Override
    public void onClick(View view) {
        int catalog = 0;
        switch (view.getId()) {
            case R.id.coffee_btn:
                catalog = 0;
                break;
            case R.id.dessert_btn:
                catalog = 1;
                break;
            case R.id.store_btn:
                catalog = 2;
                break;
        }

        if(catalog>=2){
            Toast.makeText(this, "功能未完成....",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("catalog", catalog);
        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}