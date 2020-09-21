package me.app17.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "fragment";
    private FragmentManager fragmentManager;

    private Button addBtn;
    private Button replaceBtn;
    private Button detachBtn;
    private Button attachBtn;
    private Button removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        findViews();

    }

    public void findViews() {
        addBtn = findViewById(R.id.add_btn);
        replaceBtn = findViewById(R.id.replace_btn);
        detachBtn = findViewById(R.id.detach_btn);
        attachBtn = findViewById(R.id.attach_btn);
        removeBtn=findViewById(R.id.remove_btn);

        addBtn.setOnClickListener(this);
        detachBtn.setOnClickListener(this);
        attachBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.remove_btn:
                fragment=fragmentManager.findFragmentByTag(TAG);

                if(fragment!=null){
                    FragmentTransaction transaction=fragmentManager.beginTransaction();
                    transaction.remove(fragment);
                    transaction.commit();

                    Toast.makeText(this,  fragment.getTag()+" 刪除", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "Fragment 不存在!", Toast.LENGTH_SHORT).show();


                break;
            case R.id.add_btn:
                //假如沒有fragment
                fragment = fragmentManager.findFragmentById(R.id.frameLayout);

                if (fragment == null) {
                    //將資料傳遞給Fragment並建立
                    MyFragment fragment1 = new MyFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "Fragment A");
                    fragment1.setArguments(bundle);

                    //進行新增
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.frameLayout, fragment1, TAG);
                    transaction.commit();
                    return;
                }

                Toast.makeText(this, "Fragment 存在!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.detach_btn:

                fragment = fragmentManager.findFragmentByTag(TAG);
                if (fragment != null) {
                    //不是正在執行移除狀態
                    if (!fragment.isDetached()) {
                        //進行移除
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.detach(fragment);
                        transaction.commit();
                    }
                    return;
                }

                Toast.makeText(this, "Fragment 不存在!", Toast.LENGTH_SHORT).show();
                break;
            //將被撥離的進行貼上
            case R.id.attach_btn:

                fragment = fragmentManager.findFragmentById(R.id.frameLayout);

                if (fragment == null) {
                    Toast.makeText(this, "Fragment 不存在!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //被撥離狀態
                if (fragment.isDetached()) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.attach(fragment);
                    transaction.commit();
                    return;
                }

                Toast.makeText(this, "Fragment 已貼上!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.replace_btn:

                //建立新的fragment
                MyFragment fragment2 = new MyFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Fragment B");
                fragment2.setArguments(bundle);

                //進行新增
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment2, TAG);
                transaction.commit();

                break;

        }
    }
}