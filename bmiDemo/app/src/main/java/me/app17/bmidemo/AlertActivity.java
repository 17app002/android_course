package me.app17.bmidemo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
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


public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    EditText heightEdtTxt;
    EditText weightEdtTxt;

    Button calBtn;

    private LinearLayout loginLayout;

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
        //showAlertDialog("BMI指數",msg,null);
        showLoginAlertDialog("用戶登入","",new String[]{"確定","取消"});

    }

    public void showLoginAlertDialog(String title,String message,String[] button){

        Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setIcon(R.drawable.icon6);
        //dialog.setMessage(message);

        loginLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.login_layout,null);
        dialog.setView(loginLayout);

        dialog.setPositiveButton(button[0],new loginClick());

        dialog.setNegativeButton(button[1],new cancelClick());

        dialog.create();
        dialog.show();
    }

    public void showAlertDialog(String title,String message,String[] button){

        Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setIcon(R.drawable.icon8);
        dialog.setMessage(message);

        dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.create();
        dialog.show();
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


    class cancelClick implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }


    class loginClick implements Dialog.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {

            String name=((EditText)loginLayout.findViewById(R.id.nameEdtTxt)).getText().toString();
            String password=((EditText)loginLayout.findViewById(R.id.passwordEdtTxt)).getText().toString();

            String message=String.format("輸入的是%s\n%s\n",name,password);
            Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();

            dialog.dismiss();

        }
    }

}
