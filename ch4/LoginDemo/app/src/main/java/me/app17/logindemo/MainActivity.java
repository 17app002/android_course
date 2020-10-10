package me.app17.logindemo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

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


        userName = "jerry";
        passWord = "123456";
        male = true;
        over18 = true;
        findViews();

        over18Cbx.setChecked(true);
        over18Cbx.setOnCheckedChangeListener(this);
        sexRgp.setOnCheckedChangeListener(this);
        loginBtn.setOnClickListener(this);

        findViewById(R.id.result_text).setVisibility(View.INVISIBLE);

    }

    public void findViews() {
        sexRgp = findViewById(R.id.sex_rgp);
        over18Cbx = findViewById(R.id.over18_cbx);
        loginBtn = findViewById(R.id.login_btn);
    }


    @Override
    public void onClick(View view) {
        EditText nameEdit = findViewById(R.id.user_edit);
        EditText passWordEdit = findViewById(R.id.password_edit);
        TextView resultText = findViewById(R.id.result_text);
        if (!over18) {
            resultText.setText(getResources().getString(R.string.under18));
            Toast.makeText(this, getResources().getString(R.string.under18), Toast.LENGTH_SHORT).show();
            return;
        }

        String userName = nameEdit.getText().toString();
        String passWord = passWordEdit.getText().toString();

        if (userName.equals("") || passWord.equals("")) {
            resultText.setText(getResources().getString(R.string.empty));
            Toast.makeText(this, getResources().getString(R.string.empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (nameEdit.getText().toString().equals(this.userName) &&
                passWordEdit.getText().toString().equals(this.passWord)) {
            System.out.println(getResources().getString(R.string.login_success));
            resultText.setText(getResources().getString(R.string.login_success));
            Toast.makeText(this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(getResources().getString(R.string.login_fail));
            resultText.setText(getResources().getString(R.string.login_fail));
            Toast.makeText(this, getResources().getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.over18_cbx) {
            over18 = !over18;
            Log.d(TAG, String.valueOf(over18));
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(id);

        //取得radioGroup上所有的radioButton
        int count = radioGroup.getChildCount();

        for (int j = 0; j < count; j++) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(j);
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.midden_font_size));
            button.setTextColor(Color.BLACK);
        }


        ImageView faceImg;

        if (radioButton.getId() == R.id.male_rbn) {
            male = true;
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_font_size));
            radioButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            faceImg = findViewById(R.id.face_img);
            faceImg.setImageResource(R.drawable.man);

        } else if (radioButton.getId() == R.id.female_rbn) {
            male = false;
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_font_size));
            radioButton.setTextColor(getResources().getColor(R.color.colorRed));
            faceImg = findViewById(R.id.face_img);
            faceImg.setImageResource(R.drawable.woman);

        }
        Log.d("SEX", String.valueOf(male));
    }


}