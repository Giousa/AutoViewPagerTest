package com.giousa.autoviewpagertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.giousa.autoviewpagertest.utlis.PictureUrl;
import com.giousa.autoviewpagertest.view.AutoScrollViewPager;
import com.giousa.autoviewpagertest.view.BaseViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PictureUrl{

    private BaseViewPagerAdapter<String> mBaseViewPagerAdapter;

    private AutoScrollViewPager mViewPager;

    private String[] paths = {BASE_URL+"a00.jpg",BASE_URL+"a01.jpg",BASE_URL+"a02.jpg",BASE_URL+"a03.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (AutoScrollViewPager) findViewById(R.id.viewPager);

        mBaseViewPagerAdapter = new BaseViewPagerAdapter<String>(this,listener) {
            @Override
            public void loadImage(ImageView view, int position, String url) {
                Picasso.with(MainActivity.this).load(url).into(view);
                Log.d("TAG","position="+position);
            }

            @Override
            public void setSubTitle(TextView textView, int position, String s) {
                textView.setText(s);
            }
        };
        mViewPager.setAdapter(mBaseViewPagerAdapter);

        mBaseViewPagerAdapter.add(initData());


    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < paths.length ;i++){
            data.add(paths[i]);
        }
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.onDestroy();
    }

    public void add(View view) {
        mBaseViewPagerAdapter.add(BASE_URL+"a04.jpg");
    }

    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {

        @Override
        public void onItemClick(int position, String url) {
            Toast.makeText(getApplicationContext(),
                    position + " ========= "+ url, Toast.LENGTH_SHORT).show();
        }
    };
}
