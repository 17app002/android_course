package me.app17.chapter02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editSex;
    EditText editAge;
    Button btnBMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);
//
//        editSex = (EditText) findViewById(R.id.edit_sex);
//        editAge = (EditText) findViewById(R.id.edit_age);
//        btnBMI = (Button) findViewById(R.id.btn_bmi);
//
//        String sex = editSex.getText().toString();
//        int age = Integer.valueOf(editAge.getText().toString());
        //float bmi=Double.valueOf()

        //Toast.makeText(this, )


    }
}
