package com.dragon.toolbox.hs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dragon.toolbox.databinding.LayoutHsBinding;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.HsHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<HsBean> datas;

    private static final String TAG = "HsAdapter";

    public NestedAdapter(Context context, List<HsBean> datas) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHsBinding inflate = LayoutHsBinding.inflate(layoutInflater, parent, false);
        return new HsHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HsHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");

        HsBean hsBean = datas.get(position);
        holder.layoutHsBinding.tvLayoutHs.setText(hsBean.getContent());
        ConstraintLayout root = holder.layoutHsBinding.getRoot();
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        layoutParams.height = hsBean.getSize();

        holder.layoutHsBinding.tvLayoutHs.setLifecyclerCallback(new CustomTextView.LifecyclerCallback() {
            @Override
            public void attach() {
                Log.d(TAG, "attach() called");
            }

            @Override
            public void detach() {
                Log.d(TAG, "detach() called");
            }
        });
        root.setLayoutParams(layoutParams);
        root.setBackgroundColor(hsBean.getColor());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class HsHolder extends RecyclerView.ViewHolder {

        private final LayoutHsBinding layoutHsBinding;

        public HsHolder(LayoutHsBinding layoutHsBinding) {
            super(layoutHsBinding.getRoot());
            this.layoutHsBinding = layoutHsBinding;
        }

    }

}
