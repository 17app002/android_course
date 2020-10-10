package me.app17.logindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

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
            resultText.setText("未滿18歲，請離開!");
            return;
        }

        String userName = nameEdit.getText().toString();
        String passWord = passWordEdit.getText().toString();

        if (userName.equals("") || passWord.equals("")) {
            resultText.setText("帳號或密碼不能為空!");
            return;
        }

        if (nameEdit.getText().toString().equals(this.userName) &&
                passWordEdit.getText().toString().equals(this.passWord)) {
            System.out.println("登入成功!");
            resultText.setText("登入成功!");
        } else {
            System.out.println("登入失敗....");
            resultText.setText("登入失敗.......");
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.over18_cbx) {
            over18 = !over18;
            System.out.println(over18);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int id = radioGroup.getCheckedRadioButtonId();
        male = false;
        if (id == R.id.male_rbn) {
            male = true;
        }
        System.out.println(male);
    }
}