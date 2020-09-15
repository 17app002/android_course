package me.app17.thsviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView itemLv;
    Button updateBtn;

    public static MainActivity instance;
    public static Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        uiHandler = new Handler();

        findViews();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLv.setAdapter(null);
                new Thread(new THSViewer(itemLv)).start();
            }
        });
    }

    public void findViews() {
        itemLv = findViewById(R.id.item_lv);
        updateBtn = findViewById(R.id.update_btn);
    }

    /***
     * 主線程UI-Toast更新
     * @param msg
     */
    //https://codertw.com/android-%E9%96%8B%E7%99%BC/352565/
    public static void showMessage(final String msg){
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(instance.getApplicationContext(),
                        msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}



