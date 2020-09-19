package me.app17.acivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "MainActivity";
    private EditText nameEdt;
    private EditText chineseEdt;
    private EditText englishEdt;
    private EditText mathEdt;

    private Button submitBtn;

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);


        findViews();
    }

    public void findViews() {
        nameEdt = findViewById(R.id.name_edt);
        chineseEdt = findViewById(R.id.chinese_edt);
        englishEdt = findViewById(R.id.english_edt);
        mathEdt = findViewById(R.id.math_edt);

        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            student = new Student(nameEdt.getText().toString(),
                    Double.parseDouble(chineseEdt.getText().toString()),
                    Double.parseDouble(englishEdt.getText().toString()),
                    Double.parseDouble(mathEdt.getText().toString())
            );

            Bundle bundle=new Bundle();
            bundle.putSerializable("student",student);
            Intent intent=new Intent(MainActivity.this,ResultActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


//            TextView result_txt = findViewById(R.id.result_text);
//            result_txt.setText(student.toString());

        } catch (Exception ex) {
            Log.d(TAG, "輸入有誤!");
            Toast.makeText(this, "輸入有誤!", Toast.LENGTH_SHORT).show();
            System.out.println(ex.toString());
        }
    }


}