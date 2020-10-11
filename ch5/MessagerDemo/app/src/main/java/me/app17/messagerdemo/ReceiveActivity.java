package me.app17.messagerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent=getIntent();
        String receiveMessage=intent.getStringExtra("message");
        TextView receiveText=(TextView)findViewById(R.id.receive_text);
        receiveText.setText(receiveMessage);

    }
}