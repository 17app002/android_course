package com.app17.chapter01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button loginBtn;
    EditText userName;
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginBtn=this.findViewById(R.id.login_button);
        userName=findViewById(R.id.login_username);
        passWord=findViewById(R.id.login_password);


        loginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        boolean success=userName.getText().toString().equals("root") && passWord.getText().toString().equals("root123");

        if(success){
            Toast.makeText(this,"登入成功!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"登入失敗!",Toast.LENGTH_LONG).show();
        }
    }
}
