package me.app17.bmidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText heightEdit;
    private EditText weightEdit;
    private TextView resultText;
    private Button countBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        heightEdit = findViewById(R.id.height_edit);
        weightEdit = findViewById(R.id.weight_edit);
        resultText = findViewById(R.id.result_text);
        countBtn = findViewById(R.id.count_btn);

        countBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double height = Double.valueOf(heightEdit.getText().toString());
        double weight = Double.valueOf(weightEdit.getText().toString());
        double heightM = height / 100;
        double bmi = weight / Math.pow(heightM, 2);
        System.out.println((bmi));

        ((TextView) findViewById(R.id.bmi_text)).setText(String.format("%6.2f", bmi));
        resultText.setText(getComment(bmi));
    }


    /***
     * 取得評語
     * @param bmi
     * @return
     */
    public String getComment(double bmi) {
        if (bmi >= 27) {
            return "  哦！「體重過重」了，要小心囉，趕快力行「健康體重管理」！";
        } else if (bmi >= 24) {
            return "啊～「肥胖」，建議進行「健康體重管理」囉！";
        } else if (bmi >= 18.5) {
            return "恭喜！「健康體重」，要繼續保持！";
        }
        return "「體重過輕」，需要多運動，均衡飲食，以增加體能，維持健康！";
    }
}