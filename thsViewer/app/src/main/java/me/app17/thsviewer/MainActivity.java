package me.app17.thsviewer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView itemLv;
    Button getBtn;
    Button updateBtn;
    TextView countTv;


    public static MainActivity instance;
    public static Handler uiHandler;

    private THSViewer thsViewer;


    private String startStation;
    private String endStation;
    private String date;

    private AlertDialog selectDialog;


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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(1, 1, 1, "首頁");
        menu.add(1, 2, 2, "搜尋");
        menu.add(1, 3, 3, "65折");
        menu.add(1, 4, 4, "退出");


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 1) {
            showMessage("資料重整更新...");
            thsViewer.updateAllData();
        } else if (item.getItemId() == 4) {
            ConfirmExit();
        } else if (item.getItemId() == 2) {
            showSelectDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    /***
     * 選擇地點跟日期
     */
    private void showSelectDialog(){

        final String[] station = {"南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化",
                "雲林", "嘉義", "台南", "左營"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LinearLayout layout=(LinearLayout)getLayoutInflater().inflate(R.layout.activity_select, null);

        Spinner spinner1 = (Spinner) layout.findViewById(R.id.start_spn);
        Spinner spinner2= (Spinner) layout.findViewById(R.id.end_spn);
        Button okBtn=layout.findViewById(R.id.ok_btn);

        ArrayAdapter<String> stationList = new ArrayAdapter<>(this,
                R.layout.spinner_item, station);

        spinner1.setAdapter(stationList);
        spinner2.setAdapter(stationList);

        spinner1.setSelection(0);
        spinner2.setSelection(station.length-1);

        dialog.setView(layout);
        selectDialog = dialog.create();
        selectDialog.setCanceledOnTouchOutside(false);
        selectDialog.show();

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                startStation=station[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                endStation=station[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }


    /***
     * 日期選擇
     */
    public void showDateDialog(){
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = setDateFormat(year, month, dayOfMonth);
                if (thsViewer.getMapList() == null) {
                    showMessage("請先取得資料!");
                    selectDialog.dismiss();
                    return;
                }
                selectDialog.dismiss();
                thsViewer.findData(startStation,endStation,date);

            }
        }, mYear, mMonth, mDay).show();
    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.format("%4d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        uiHandler = new UIHandler();
        findViews();
        thsViewer = new THSViewer(itemLv);

    }


    @Override
    protected void onResume() {
        super.onResume();

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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ConfirmExit() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle("離開");
        ad.setMessage("確定要離開此程式嗎?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                MainActivity.this.finish();
            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        ad.show();
    }
}



