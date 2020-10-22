package me.app17.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    private Spinner spinner;
    //紀錄是否重新輸入
    private boolean newStart;

    private String decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        numberText = findViewById(R.id.number_text);
        spinner = findViewById(R.id.decimal_spr);
        numberText.setMaxLines(3);
        newStart = true;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                decimal = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

//
//    public void onClick(View v) {
//
//        int[] resId = {R.id.n1_btn, R.id.n2_btn, R.id.n3_btn,
//                R.id.n4_btn, R.id.n5_btn, R.id.n6_btn, R.id.n7_btn,
//                R.id.n8_btn, R.id.n9_btn, R.id.n0_btn, R.id.add_btn,
//                R.id.dec_btn, R.id.multi_btn,
//                R.id.div_btn,R.id.dot_btn};
//
//        for(int id:resId){
//            if(v.getId()==id){
//                addNumberText(((Button)v).getText().toString());
//            }
//        }
//
//    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_btn:
                clear();
                break;
            case R.id.n0_btn:
                addNumberText("0");
                break;
            case R.id.n1_btn:
                addNumberText("1");
                break;
            case R.id.n2_btn:
                addNumberText("2");
                break;
            case R.id.n3_btn:
                addNumberText("3");
                break;
            case R.id.n4_btn:
                addNumberText("4");
                break;
            case R.id.n5_btn:
                addNumberText("5");
                break;
            case R.id.n6_btn:
                addNumberText("6");
                break;
            case R.id.n7_btn:
                addNumberText("7");
                break;
            case R.id.n8_btn:
                addNumberText("8");
                break;
            case R.id.n9_btn:
                addNumberText("9");
                break;
            case R.id.add_btn:
                addNumberText("+");
                break;
            case R.id.dec_btn:
                addNumberText("-");
                break;
            case R.id.multi_btn:
                addNumberText("*");
                break;
            case R.id.dot_btn:
                addNumberText(".");
                break;
            case R.id.div_btn:
                addNumberText("/");
                break;
            case R.id.ans_btn:
                answer();
                break;
        }
    }

    /***
     * 引入JavaScript engine
     */
    public void answer() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try {
            String result = String.valueOf(engine.eval(numberText.getText().toString()));
            numberText.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
            newStart = true;


            if (!decimal.equals("")) {
                String format = String.format(".%s", decimal);
                result = String.format("%" + format + "f", Double.valueOf(result));
                Log.d("MainActivity", result);
            }

            //result = String.format("%.2f", Double.valueOf(result));

            //檢查小數點尾數是否是0
            String[] temp = result.split("\\.");
            if (Long.valueOf(temp[1]).equals(0)) {
                result = temp[0];
            }

            numberText.setText(result);
            Log.d("ManActivity", result);

        } catch (ScriptException e) {
            Toast.makeText(this, "計算錯誤!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear() {
        numberText.setText("0");
        newStart = true;
        numberText.setGravity(Gravity.LEFT);
    }

    public void addNumberText(String number) {
        //去除第一個零
        if (newStart) {
            numberText.setText("");
            numberText.setGravity(Gravity.LEFT);
            newStart = false;
        }
        numberText.setText(numberText.getText() + number);
    }
}