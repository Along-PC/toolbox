package com.dragon.toolbox.thumbnail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dragon.toolbox.R;

import java.util.List;

public class SmallImageAdapter extends RecyclerView.Adapter<SmallImageAdapter.SmallImageHolder> {

    private String path;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Long> extractPoints;

    private OnItemClickListener onItemClickListener;

    public SmallImageAdapter(Context context,String path, List<Long> extractPoints) {
        this.context = context;
        this.path=path;
        this.extractPoints = extractPoints;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SmallImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SmallImageHolder(layoutInflater.inflate(R.layout.item_small_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SmallImageHolder holder, int position) {
        Glide.with(context).asBitmap().load(path).frame(extractPoints.get(position)*1000).thumbnail(
                Glide.with(context).asBitmap().load(path)
        ).into(holder.mImgItemSmallImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.frame(extractPoints.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return extractPoints == null ? 0 : extractPoints.size();
    }

    public class SmallImageHolder extends RecyclerView.ViewHolder {

        private ImageView mImgItemSmallImage;

        public SmallImageHolder(@NonNull View itemView) {
            super(itemView);
            mImgItemSmallImage = (ImageView) itemView.findViewById(R.id.img_item_small_image);
        }
    }

    public interface OnItemClickListener{

        public void frame(long time);
    }

}
