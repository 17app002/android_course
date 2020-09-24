package me.app17.phonecount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void click(View view) {

        EditText editText = findViewById(R.id.phone_number);

        String number = editText.getText().toString();

        System.out.println(number.toCharArray());

        int total = 0;

        for (char c : number.toCharArray()) {
            total += c - 48;
        }

        System.out.println("總計為:" + total);

        TextView textView = findViewById(R.id.result);

        textView.setText(String.valueOf(total));
    }
}