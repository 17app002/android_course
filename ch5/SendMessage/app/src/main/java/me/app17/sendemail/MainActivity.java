package me.app17.sendemail;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button sendBtn;
    private Spinner emailCCSpr;
    private EditText messageEdit;
    private EditText toEdit;
    private EditText subjectEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        //使用程式進行設定
        //((TextView) findViewById(R.id.message_edit)).setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailCC = String.valueOf(emailCCSpr.getSelectedItem());
                String message = messageEdit.getText().toString();
                String to = toEdit.getText().toString();
                String subject = subjectEdit.getText().toString();

                if (to.equals("") || subject.equals("") || message.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getResources().getString(R.string.send) + " " + to);
                alertDialog.setMessage("cc:" + emailCC + "\n" + subject);

                alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        sendEmail();
                        Toast.makeText(MainActivity.this, "發送中....", Toast.LENGTH_LONG).show();
                    }
                });

                alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertDialog.create();
                alertDialog.show();
                //sendEmail();
            }
        });

        //spinner
        emailCCSpr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String emailCC = String.valueOf(adapterView.getSelectedItem());
                Log.i("spinner_test", emailCC);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void findViews() {
        sendBtn = findViewById(R.id.send_btn);
        emailCCSpr = findViewById(R.id.emailcc_spr);
        messageEdit = findViewById(R.id.message_edit);
        toEdit = findViewById(R.id.to_edit);
        subjectEdit = findViewById(R.id.subject_edit);
    }

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {toEdit.getText().toString()};
        String[] CC = {String.valueOf(emailCCSpr.getSelectedItem())};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectEdit.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageEdit.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}