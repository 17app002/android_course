package me.app17.ch1_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText number1Edit;
    private EditText number2Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);
        number1Edit = findViewById(R.id.number1_edit);
        number2Edit = findViewById(R.id.number2_edit);
    }

    public void countClick(View view) {


        try {
            int number1 = Integer.valueOf(number1Edit.getText().toString());
            int number2 = Integer.valueOf(number2Edit.getText().toString());

            //System.out.println(number1 + number2);
            String result = String.valueOf(number1 + number2);
            resultText.setText(result);

        }catch (Exception e){
            e.printStackTrace();
            resultText.setText("資料輸入錯誤!");
        }

    }
}


