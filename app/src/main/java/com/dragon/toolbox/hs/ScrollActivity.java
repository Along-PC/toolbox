package com.dragon.toolbox.hs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dragon.toolbox.databinding.ActivityScroll2Binding;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScroll2Binding binding = ActivityScroll2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] colors={0xFFF44336,0xFF4CAF50,0xFF00BCD4};

        List<HsBean> datas=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int color=colors[i%colors.length];
            int width=DensityUtils.dp2px(this,100);
            if(i==0 || i==29){
                width=DensityUtils.dp2px(this,150);
            }
            HsBean hsBean = new HsBean(color, "msg" + i,width);
            datas.add(hsBean);
        }

        HsAdapter hsAdapter = new HsAdapter(this, datas);
        binding.recyclerActivityScroll2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        binding.recyclerActivityScroll2.setAdapter(hsAdapter);

        for (HsBean data : datas) {
            TextView textView = new TextView(this);
            int width=data.getSize();
            int height= ViewGroup.LayoutParams.MATCH_PARENT;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width,height);
            textView.setLayoutParams(layoutParams);
            textView.setText(data.getContent());
            textView.setBackgroundColor(data.getColor());
            textView.setGravity(Gravity.CENTER);
            binding.llActivityScroll2.addView(textView);
        }
        binding.hsActivityScroll2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                int range = binding.recyclerActivityScroll2.computeHorizontalScrollRange();
                Log.d("player", "range:" + range);
                int max = range - binding.recyclerActivityScroll2.getWidth();
                float percent = (scrollX) * 1.0f / max;
                int offset = binding.recyclerActivityScroll2.computeHorizontalScrollOffset();
                int scrollBy = (int) (percent * max - offset);
                Log.d("player", "start scrollBy:" + scrollBy);
                Log.d("player", "max:" + max);
                Log.d("player", "scrollX:" + scrollX);
                int absoluteOffset = offset + scrollBy;
                if (absoluteOffset < max && absoluteOffset >= 0) {
                    Log.d("player", "end scrollBy:" + scrollBy);
                    Log.d("player", "absoluteOffset:" + absoluteOffset);
                    binding.recyclerActivityScroll2.scrollBy(scrollBy, 0);
                }

            }
        });
    }
}