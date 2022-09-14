package com.dragon.toolbox.scroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dragon.toolbox.BuildConfig;
import com.dragon.toolbox.R;
import com.dragon.toolbox.hs.DensityUtils;

import java.util.ArrayList;

public class ScrollActivity extends AppCompatActivity {

    private RecyclerView mRecyclerActivityScroll;
    private RecyclerView mNestedActivityScroll;
    private LinearLayout mLlActivityScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mRecyclerActivityScroll = (RecyclerView) findViewById(R.id.recycler_activity_scroll);
        mNestedActivityScroll = (RecyclerView) findViewById(R.id.nested_activity_scroll);
        mLlActivityScroll = (LinearLayout) findViewById(R.id.ll_activity_scroll);

        initFruit();

        initCity();

        initNested();

    }

    private void initNested() {

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("nested"+i);
        }

        for (String data : datas) {
            TextView textView = new TextView(this);
            int width= ViewGroup.LayoutParams.MATCH_PARENT;
            int height= DensityUtils.dp2px(this,80);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width,height);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setText(data);
        }

    }

    private void initFruit() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("fruit"+i);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, strings);
        mRecyclerActivityScroll.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerActivityScroll.setAdapter(simpleAdapter);
    }

    private void initCity() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("city"+i);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, strings);
        mNestedActivityScroll.setLayoutManager(new LinearLayoutManager(this));
        mNestedActivityScroll.setAdapter(simpleAdapter);
    }

}