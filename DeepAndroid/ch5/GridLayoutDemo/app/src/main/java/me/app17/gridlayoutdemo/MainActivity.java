package me.app17.gridlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    private boolean first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberText=findViewById(R.id.number_text);
        first=true;

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_btn:
                numberText.setText("0");
                first=true;
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
            case R.id.add_btn:
                addNumberText("+");
                break;
            case R.id.dec_btn:
                addNumberText("-");
                break;
            case R.id.multi_btn:
                addNumberText("*");
                break;
            case R.id.div_btn:
                addNumberText("/");
                break;
            case R.id.ans_btn:
                answer();
                break;
        }
    }

    public void answer(){
        List<String> stringList=new ArrayList<String>();

        String inputNumber=numberText.getText().toString();


    }

    public void addNumberText(String number){
        //去除第一個零
        if(first){
            numberText.setText("");
            first=false;
        }
        numberText.setText(numberText.getText()+number);
    }
}