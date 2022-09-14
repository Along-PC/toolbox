package com.dragon.toolbox.hs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dragon.toolbox.R;
import com.dragon.toolbox.databinding.ActivityNestedBinding;

import java.util.ArrayList;
import java.util.List;

public class NestedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNestedBinding binding = ActivityNestedBinding.inflate(getLayoutInflater());
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
        binding.reyclerActivityNested.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        binding.reyclerActivityNested.setAdapter(hsAdapter);
    }
}