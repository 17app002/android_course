package me.app17.bmidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    private TextView reportTv;
    private TextView bmiResultTv;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);

        reportTv = findViewById(R.id.reportTv);
        bmiResultTv = findViewById(R.id.bmiResultTv);
        backBtn = findViewById(R.id.backBtn);

        Bundle bundle = getIntent().getExtras();

        float height = bundle.getFloat("height");
        float weight = bundle.getFloat("weight");
        String sex = bundle.getString("sex");

        double bmi = weight / Math.pow(height / 100, 2);

        String bmiStr = String.format("BMI:%.2f", bmi);

        reportTv.setText(bmiStr);

        bmiResultTv.setText(getComment(bmi, sex));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(BmiActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public String getComment(double bmi, String sex) {

        String result = sex;

        if (bmi >= 27) {
            result += "  哦！「體重過重」了，要小心囉，趕快力行「健康體重管理」！";
        } else if (bmi >= 24) {
            result += "啊～「肥胖」，建議進行「健康體重管理」囉！";
        } else if (bmi >= 18.5) {
            result += "恭喜！「健康體重」，要繼續保持！";
        }
        result += "「體重過輕」，需要多運動，均衡飲食，以增加體能，維持健康！";

        return result;
    }

}
