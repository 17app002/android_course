package com.example.viewpagedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * https://codertw.com/android-%E9%96%8B%E7%99%BC/331678/
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private List<View> viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        pagerTabStrip = findViewById(R.id.tab_strip);

        final int[] tabViewId = {R.layout.view1_layout,
                R.layout.view2_layout, R.layout.view3_layout};

        viewList = new ArrayList<>();

        //View view1=LayoutInflater.from(this).inflate(R.layout.view1_layout, null)
        for (int id : tabViewId) {
            viewList.add(LayoutInflater.from(this).inflate(id, null));
        }

        //新增tab
        List<String> tabList = new ArrayList<>();
        tabList.addAll(Arrays.asList(new String[]{"個人簡介", "學經歷", "作品介紹"}));


        pagerTabStrip = (PagerTabStrip) this.findViewById(R.id.tab_strip);
        //取消tab下面的長橫線
        pagerTabStrip.setDrawFullUnderline(false);
        //設定tab的背景色
        pagerTabStrip.setBackgroundResource(R.color.colorAccent);
        //設定當前tab頁籤的下劃線顏色
        pagerTabStrip.setTabIndicatorColorResource(R.color.red);

        pagerTabStrip.setTextSpacing(400);

        viewPager.setAdapter(new MyPageAdapter(viewList, tabList));

    }


}


class MyPageAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> tabList;

    MyPageAdapter(List<View> viewList, List<String> tabList) {
        this.viewList = viewList;
        this.tabList = tabList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    //滑動切換的時候銷燬當前的元件
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(viewList.get(position));
    }

    @NonNull
    @Override
    //將當前檢視新增到container中並返回當前View檢視
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}