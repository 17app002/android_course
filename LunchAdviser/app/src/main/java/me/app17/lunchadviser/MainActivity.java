package me.app17.lunchadviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String lunchStr = String.valueOf(spinner.getSelectedItem());
        System.out.println(lunchStr);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(lunchStr);
    }
}