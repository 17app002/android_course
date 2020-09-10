package me.app17.ch02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.app17.ch02.R;

public class MainActivity extends AppCompatActivity {
    EditText editHeight;
    EditText editWeight;
    EditText editSex;
    EditText editAge;
    TextView textBmi;
    TextView textComment;
    Button btnBMI;

    MainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout1);
        this.setTitle(R.string.app_name);

        editHeight = (EditText) findViewById(R.id.edit_height);
        editWeight = (EditText) findViewById(R.id.edit_weight);
        editSex = (EditText) findViewById(R.id.edit_sex);
        editAge = (EditText) findViewById(R.id.edit_age);

        btnBMI = (Button) findViewById(R.id.btn_bmi);
        textBmi = findViewById(R.id.text_bmi);
        textComment = findViewById(R.id.text_comment);
        instance = this;

//        editHeight.setText("165");
//        editWeight.setText("58");

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strHeight = editHeight.getText().toString();
                String strWidth = editWeight.getText().toString();

                if (strHeight.equals("") || strWidth.equals("")) {
                    Toast.makeText(instance.getBaseContext(), "輸入錯誤!", Toast.LENGTH_SHORT).show();
                }

                try {
                    double height = Double.parseDouble(strHeight);
                    double weight = Double.parseDouble(strWidth);

                    System.out.println(height + " " + weight);
                    double bmi = weight / Math.pow(((height) / 100), 2);
                    textBmi.setText(String.format("%3.2f", bmi));
                    textComment.setText(getComment(bmi));

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(instance.getBaseContext(), "數值輸入錯誤!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


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
