package me.app17.ch03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.app17.ch03.R;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    Button okBtn;
    TextView userNameTv;
    CheckBox school1Chk;
    CheckBox school2Chk;
    CheckBox school3Chk;
    CheckBox school4Chk;

    List<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okBtn = findViewById(R.id.ok_btn);
        userNameTv = findViewById(R.id.result_tv);

        school1Chk = findViewById(R.id.school_chk1);
        school2Chk = findViewById(R.id.school_chk2);
        school3Chk = findViewById(R.id.school_chk3);
        school4Chk = findViewById(R.id.school_chk4);


        okBtn.setOnClickListener(this);


        checkBoxList = new ArrayList<>();
        checkBoxList.add(school1Chk);
        checkBoxList.add(school2Chk);
        checkBoxList.add(school3Chk);
        checkBoxList.add(school4Chk);

    }

    @Override
    public void onClick(View v) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);

        int idRbtn = radioGroup.getCheckedRadioButtonId();
        RadioButton selectRbtn = (RadioButton) findViewById(idRbtn);
        String resultStr = "您選擇的是" + selectRbtn.getText().toString() + "課程\n";
        //取得選擇的buttonId
//        int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
//        RadioButton radioButton=(RadioButton)radioGroup.getChildAt(index);

        resultStr += "預計上課的地點有:\n";

        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                resultStr += checkBox.getText().toString() + "\n";
            }
        }
        userNameTv.setText(resultStr);

    }


}
