package me.app17.thsviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class SelectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final String[] station = {"南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化",
            "雲林", "嘉義", "台南", "左營"};

    private String startStation;
    private String endStation;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Spinner spinner1 = (Spinner) findViewById(R.id.start_spn);
        Spinner spinner2= (Spinner) findViewById(R.id.end_spn);
        Button okBtn=findViewById(R.id.ok_btn);

        ArrayAdapter<String> stationList = new ArrayAdapter<>(SelectActivity.this,
                R.layout.spinner_item, station);

        spinner1.setAdapter(stationList);
        spinner2.setAdapter(stationList);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        spinner1.setSelection(0);
        spinner2.setSelection(station.length-1);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        if( adapterView.getId()==R.id.start_spn){
            startStation= station[i];
        }else if(adapterView.getId()==R.id.end_spn){
            endStation=station[i];
        }

        //Toast.makeText(this, "您選擇了:" + station[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void showDateDialog(){
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = setDateFormat(year, month, dayOfMonth);

                Intent intent=new Intent();
                intent.setClass(SelectActivity.this,MainActivity.class);

                Bundle bundle=new Bundle();
                bundle.putStringArray("data",new String[]{startStation,endStation,date});
                Toast.makeText(SelectActivity.this, "您選擇了:" + date+ " "+startStation+" "+endStation, Toast.LENGTH_SHORT).show();
                //showMessage(date);

                intent.putExtras(bundle);
                SelectActivity.this.finish();
                startActivityForResult(intent,0);

            }
        }, mYear, mMonth, mDay).show();
    }


    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.format("%4d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
    }
}