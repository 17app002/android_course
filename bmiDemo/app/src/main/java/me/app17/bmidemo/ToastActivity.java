package me.app17.bmidemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    enum Sex {MALE, FEMALE};

    EditText heightEdtTxt;
    EditText weightEdtTxt;

    Button calBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        findViews();
        setListeners();
    }

    public void findViews() {
        heightEdtTxt = findViewById(R.id.heightEdtTxt);
        weightEdtTxt = findViewById(R.id.weightEdtTxt);
        calBtn = findViewById(R.id.calBtn);

        heightEdtTxt.setText("165");
        weightEdtTxt.setText("58");
    }

    public void setListeners() {
        calBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //檢查是否未輸入
        if (heightEdtTxt.getText().equals("") || weightEdtTxt.getText().equals("")) {
            Toast.makeText(this, "輸入不能為空", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.valueOf(heightEdtTxt.getText().toString());
        double weight = Double.valueOf(weightEdtTxt.getText().toString());

        //取得radioButton選項
        int resourceId = ((RadioGroup) findViewById(R.id.sexRdoGup)).getCheckedRadioButtonId();
        String  sex = ((RadioButton) findViewById(resourceId)).getText().toString();
        double bmi = weight / Math.pow(height / 100, 2);

        String msg = String.format("您身高為:%.1f 體重:%.1f\nBMI指數為:%.1f\n", height, weight, bmi);

        msg+=getComment(bmi,sex);

        Toast toast=Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,80);


        LinearLayout layout=(LinearLayout)toast.getView();
        ImageView iconIv=new ImageView(this);
        iconIv.setImageResource(R.drawable.icon1);
        layout.addView(iconIv,0);

        toast.show();

    }

    public String getComment(double bmi, String sex) {

        String result = sex+"\n";

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
