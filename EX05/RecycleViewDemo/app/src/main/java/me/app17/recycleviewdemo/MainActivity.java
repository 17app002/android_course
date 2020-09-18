package me.app17.recycleviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView ItemLv = findViewById(R.id.item_lv);

        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student((byte) 1, "Jerry", 35));
        studentList.add(new Student((byte) 0, "Mary", 25));
        studentList.add(new Student((byte) 0, "Angel", 17));
        studentList.add(new Student((byte) 1, "Michael", 55));
        studentList.add(new Student((byte) 1, "Kevin", 6));
        studentList.add(new Student((byte) 0, "Michelle", 20));
        studentList.add(new Student((byte) 1, "Tom", 66));
        studentList.add(new Student((byte) 1, "Goodman", 33));

        ItemLv.setAdapter(new StudentAdapter(this, studentList));

        ItemLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,
                        student.getName() + " " + student.getAge(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

class Student {
    private byte sex;
    private String name;
    private int age;

    public byte getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student(byte sex, String name, int age) {
        this.sex = sex;
        this.name = name;
        this.age = age;
    }
}

/***
 * 學生列表適配器
 */
class StudentAdapter extends BaseAdapter {

    Context context;
    List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_view, parent, false);
        }

        Student student = studentList.get(position);

        ImageView faceIv = convertView.findViewById(R.id.face_iv);
        if (student.getSex() == 0) {
            faceIv.setImageResource(R.drawable.female);
        } else {
            faceIv.setImageResource(R.drawable.man);
        }

        TextView nameTv = convertView.findViewById(R.id.name_tv);
        nameTv.setText(student.getName());

        TextView ageTv = convertView.findViewById(R.id.age_tv);
        ageTv.setText(String.valueOf(student.getAge()));

        return convertView;
    }
}