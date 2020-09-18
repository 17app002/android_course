package me.app17.spinnerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private Spinner spinner1;
    private Spinner spinner2;

    private TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        infoTv = (TextView) findViewById(R.id.info_tv);

        setSpinner();
    }

    public void setSpinner() {

        String[] function = {"電影院", "餐廳", "旅遊景點"};

        //產生字串陣列適配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, function);

        //設定下拉樣式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner1.setSelection(0, true);
        spinner1.setOnItemSelectedListener(this);


        String[] location = {"台北", "新北市", "桃園", "台中", "台南", "高雄"};

        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(0, true);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //infoTv.setText(parent.getItemAtPosition(position).toString());
        infoTv.setText(spinner1.getSelectedItem().toString() +" "
                + spinner2.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}