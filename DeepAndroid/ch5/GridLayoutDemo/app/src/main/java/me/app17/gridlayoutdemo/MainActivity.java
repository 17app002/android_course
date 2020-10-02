package me.app17.gridlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    //紀錄是否重新輸入
    private boolean first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        numberText = findViewById(R.id.number_text);
        numberText.setMovementMethod(new ScrollingMovementMethod());
        first = true;

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_btn:
                addNumberText("0");
                first = true;
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
            Object result = (double) engine.eval(numberText.getText().toString());
            numberText.setGravity(Gravity.RIGHT);
            first = true;
            numberText.setText(result.toString());

        } catch (ScriptException e) {
            Toast.makeText(this, "Exception Raised", Toast.LENGTH_SHORT).show();
        }
    }

    public void addNumberText(String number) {
        //去除第一個零
        if (first) {
            numberText.setText("");
            numberText.setGravity(Gravity.LEFT);
            first = false;
        }
        numberText.setText(numberText.getText() + number);
    }
}