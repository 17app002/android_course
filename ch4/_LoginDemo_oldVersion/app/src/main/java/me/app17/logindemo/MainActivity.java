package me.app17.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RadioGroup sexRgp;
    private Button loginBtn;
    private CheckBox over18Cbx;

    //紀錄性別跟是否滿18歲變數
    private boolean male;
    private boolean over18;

    private String userName;
    private String passWord;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        male = true;
        over18 = false;
        userName = "jerry";
        passWord = "123456";
        findViews();


        //登入按鈕偵測
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message="";
                EditText nameEdit = findViewById(R.id.user_edit);
                EditText passWordEdit = findViewById(R.id.password_edit);
                TextView resultText = findViewById(R.id.result_text);

                if (!over18) {
                    message=getResources().getString(R.string.under18);
                    resultText.setText(getResources().getString(R.string.under18));

                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nameEdit.getText().toString().equals(userName) &&
                        passWordEdit.getText().toString().equals(passWord)) {
                    Log.d(TAG, getResources().getString(R.string.loginSuccess));
                    message=getResources().getString(R.string.loginSuccess);
                    resultText.setText(getResources().getString(R.string.loginSuccess));
                } else {
                    message=getResources().getString(R.string.loginSuccess);
                    resultText.setText(getResources().getString(R.string.loginFail));
                }

                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });

        //選取按鈕
        over18Cbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (over18Cbx.isChecked()) {
                    over18 = true;
                    return;
                }

                over18 = false;
            }
        });


        //選項改變偵測
        sexRgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //初始radioButton設定
                RadioButton maleButton = findViewById(R.id.male_rbn);
                RadioButton femaleRbn = findViewById(R.id.female_rbn);
                maleButton.setTextSize(16);
                femaleRbn.setTextSize(16);
                maleButton.setTextColor(Color.BLACK);
                femaleRbn.setTextColor(Color.BLACK);

                //取得選擇的項目
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(id);
                radioButton.setTextSize(24);
                //16進制設定顏色
                //radioButton.setTextColor(Color.parseColor("#FF0000"));
                radioButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                radioButton.setChecked(true);
                male = true;

                if (id == R.id.female_rbn) {
                    male = false;
                    ((ImageView) findViewById(R.id.face_img)).setImageResource(R.drawable.woman);
                } else {
                    ((ImageView) findViewById(R.id.face_img)).setImageResource(R.drawable.man);
                }


                Log.d("SEX RadioButton", "sex:"+String.valueOf(male));
            }
        });
    }

    public void findViews() {
        sexRgp = findViewById(R.id.sex_rgp);
        over18Cbx = findViewById(R.id.over18_cbx);
        loginBtn = findViewById(R.id.login_btn);

        over18Cbx.setChecked(false);
    }


    //一次初始所有RadioGroup所有的RadioButton
    public void resetAllRadioButton() {
        int count = sexRgp.getChildCount();
        for (int i = 0; i < count; i++) {
            View radioButton = sexRgp.getChildAt(i);
            if (radioButton instanceof RadioButton) {
                ((RadioButton) radioButton).setTextSize(16);
                ((RadioButton) radioButton).setTextColor(Color.BLACK);
            }
        }
    }

}