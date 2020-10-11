package me.app17.messagerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button sendBtn;
    private EditText messageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn=(Button)findViewById(R.id.send_btn);
        messageEdit=(EditText)findViewById(R.id.message_edit);

        sendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String message=messageEdit.getText().toString();
//        Intent intent=new Intent(this,ReceiveActivity.class);
//        intent.putExtra("message",message);

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(intent);
    }
}