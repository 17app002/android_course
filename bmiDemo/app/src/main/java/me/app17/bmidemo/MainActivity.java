package me.app17.bmidemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum Sex {MALE, FEMALE};

    EditText heightEdtTxt;
    EditText weightEdtTxt;

    Button calBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        heightEdtTxt = findViewById(R.id.heightEdtTxt);
        weightEdtTxt = findViewById(R.id.weightEdtTxt);
        calBtn = findViewById(R.id.calBtn);
        calBtn.setOnClickListener(this);

        //heightEdtTxt.setText("165.5");
        //weightEdtTxt.setText("57.5");
        //System.out.println(Sex.MALE);
    }


    @Override
    public void onClick(View v) {

        String height = heightEdtTxt.getText().toString();
        String weight = weightEdtTxt.getText().toString();
        //檢查是否未輸入
        if (height.equals("") || weight.equals("")) {
            Toast.makeText(this, "輸入不能為空", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putFloat("height", Float.valueOf(height));
        bundle.putFloat("weight", Float.valueOf(weight));

        //取得radioButton選項
        RadioGroup radioGroup = findViewById(R.id.sexRdoGup);
        int resourceId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(resourceId);

        bundle.putString("sex", radioButton.getText().toString());


        intent.putExtras(bundle);
        intent.setClass(MainActivity.this, BmiActivity.class);

        startActivity(intent);
        finish();

    }


}
