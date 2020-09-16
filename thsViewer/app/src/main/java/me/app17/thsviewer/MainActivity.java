package me.app17.thsviewer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView itemLv;
    Button getBtn;
    Button updateBtn;
    TextView countTv;


    public static MainActivity instance;
    public static Handler uiHandler;

    private THSViewer thsViewer;

    final class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //資料庫資料讀取完畢
                case 1:
                    countTv.setText(String.valueOf(thsViewer.getListCount()));
                    updateBtn.setEnabled(true);
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        uiHandler = new UIHandler();

        findViews();
        thsViewer = new THSViewer(itemLv);

        //showLoadingDialog();
    }

    public void findViews() {
        itemLv = findViewById(R.id.item_lv);
        getBtn = findViewById(R.id.get_btn);
        updateBtn = findViewById(R.id.update_btn);
        countTv = findViewById(R.id.count_tv);
        getBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);

        updateBtn.setEnabled(false);
    }

    /***
     * 主線程UI-Toast更新
     * @param msg
     */
    //https://codertw.com/android-%E9%96%8B%E7%99%BC/352565/
    public static void showMessage(final String msg) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(instance.getApplicationContext(),
                        msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        //取得資料按鈕
        if (v.getId() == R.id.get_btn) {
            getBtn.setEnabled(false);
            if (thsViewer.getMapList() != null) {
                Toast.makeText(getApplicationContext(), "目前已經是最新資料", Toast.LENGTH_SHORT).show();
                return;
            }

            itemLv.setAdapter(null);
            thsViewer.update();
            return;
        }

        //更新資料
        if (v.getId() == R.id.update_btn) {
            new AlertDialog.Builder(this)
                    .setTitle("更新資料?")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemLv.setAdapter(null);
                            thsViewer.update();
                        }
                    }).setNegativeButton("取消", null).create()
                    .show();

            return;
        }
    }

    public void showLoadingDialog() {



    }
}



