package me.app17.acivitydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private Button backBtn;
    private TextView resultTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        backBtn=findViewById(R.id.back_btn);
        resultTxt=findViewById(R.id.result_text);

        Student student=(Student)this.getIntent().getExtras().getSerializable("student");

        resultTxt.setText(student.toString());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
