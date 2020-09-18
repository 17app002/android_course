package me.app17.recycleviewdemo;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class RecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);


        RecyclerView recyclerView = findViewById(R.id.recycle_view);

        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student(1, (byte) 1, "Jerry", 35));
        studentList.add(new Student(2, (byte) 0, "Mary", 25));
        studentList.add(new Student(3, (byte) 0, "Angel", 17));
        studentList.add(new Student(4, (byte) 1, "Michael", 55));
        studentList.add(new Student(5, (byte) 1, "Kevin", 6));
        studentList.add(new Student(6, (byte) 0, "Michelle", 20));
        studentList.add(new Student(7, (byte) 1, "Tom", 66));
        studentList.add(new Student(8, (byte) 1, "Goodman", 33));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        recyclerView.setAdapter(new StudentRecyclerAdapter(this, studentList));

    }
}


/***
 * 學生列表適配器
 */
class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.MyViewHolder> {
    Context context;
    List<Student> studentList;


    public StudentRecyclerAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Student student = studentList.get(position);
        if (student.getSex() == 0) {
            holder.faceImage.setImageResource(R.drawable.female);
        } else {
            holder.faceImage.setImageResource(R.drawable.man);
        }

        holder.nameText.setText(student.getName());
        holder.ageText.setText(String.valueOf(student.getAge()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(context);
                if (student.getSex() == 0) {
                    imageView.setImageResource(R.drawable.female);
                } else {
                    imageView.setImageResource(R.drawable.man);
                }

                Toast toast = new Toast(context);
                toast.setView(imageView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return studentList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView faceImage;
        TextView nameText;
        TextView ageText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            faceImage = (ImageView) itemView.findViewById(R.id.face_iv);
            nameText = (TextView) itemView.findViewById(R.id.name_tv);
            ageText = (TextView) itemView.findViewById(R.id.age_tv);

        }
    }
}