package me.app17.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private final static String TAG="MyFragment";
    private String title;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        title=getArguments().getString("title");
        Log.d(TAG,title+":onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,title+":onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG,title+":onCreateView");

        View view=inflater.inflate(R.layout.fragment_layout,container,false);
        TextView textView=view.findViewById(R.id.fragment_tv);
        textView.setText(title);

        return view;

    }
}
