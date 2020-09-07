package me.app17.chapter03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOk;
    TextView textResult;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    List<CheckBox> checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = findViewById(R.id.btn_ok);
        textResult = findViewById(R.id.text_result);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);


        btnOk.setOnClickListener(this);


        checkboxes = new ArrayList<>();
        checkboxes.add(checkBox1);
        checkboxes.add(checkBox2);
        checkboxes.add(checkBox3);
        checkboxes.add(checkBox4);


    }

    @Override
    public void onClick(View v) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);

        int radioBthId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioBtn = (RadioButton) findViewById(radioBthId);
        String result = "您選擇的是" + radioBtn.getText().toString() + "課程\n";
        //取得選擇的buttonId
//        int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
//        RadioButton radioButton=(RadioButton)radioGroup.getChildAt(index);

        result += "預計上課的地點有:\n";

        for (CheckBox checkBox : checkboxes) {
            if (checkBox.isChecked()) {
                result += checkBox.getText().toString() + "\n";
            }
        }
        textResult.setText(result);

    }


}
