package me.app17.lunchadviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/***
 * 修改為提供早午晚餐的餐點
 * 新增FoodProvide類別
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    public void findViews() {
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new ButtonClick());
    }


    @Override
    public void onClick(View v) {
        StringBuilder stringBuilder = new StringBuilder();

        List<String> arrayList = new FoodProvide().getFood(spinner.getSelectedItem().toString());

        for (String s : arrayList) {
            stringBuilder.append(s).append("\n");
        }

        textView.setText(stringBuilder);
    }

    class ButtonClick implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            StringBuilder stringBuilder = new StringBuilder();

            List<String> arrayList = new FoodProvide().getFood(spinner.getSelectedItem().toString());

            for (String s : arrayList) {
                stringBuilder.append(s).append("\n");
            }

            textView.setText(stringBuilder);
        }
    }
}