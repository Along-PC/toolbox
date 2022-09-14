package com.dragon.toolbox.scroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dragon.toolbox.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> data;

    public SimpleAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleViewHolder(layoutInflater.inflate(R.layout.item_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        String s = data.get(position);
        holder.mTvItemSimple.setText(s);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvItemSimple;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvItemSimple = (TextView) itemView.findViewById(R.id.tv_item_simple);
        }
    }
}
