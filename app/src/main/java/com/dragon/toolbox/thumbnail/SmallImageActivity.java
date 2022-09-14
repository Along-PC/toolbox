package com.dragon.toolbox.thumbnail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dragon.toolbox.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.permissionx.guolindev.PermissionCollection;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmallImageActivity extends AppCompatActivity {

    private Button mBtSmallImage;
    private RecyclerView mRecyclerSmallImage;
    private ImageView mImgSmallImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_image);
        mBtSmallImage = (Button) findViewById(R.id.bt_small_image);
        mRecyclerSmallImage = (RecyclerView) findViewById(R.id.recycler_small_image);
        mImgSmallImage = (ImageView) findViewById(R.id.img_small_image);

        mBtSmallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        PermissionX
                .init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            choosePhoto();
                        }
                    }
                });
    }

    private void choosePhoto() {
        PictureSelector
                .create(this)
                .openGallery(PictureMimeType.ofVideo())
                .imageEngine(GlideEngine.createGlideEngine())
                .isPageStrategy(true, 30, true)
                .maxSelectNum(1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        if (result == null || result.size() == 0) {
                            return;
                        }
//                        loadThumbnail(result.get(0).getPath());
                        loadThumbnail("https://vcdn.exampleol.com/shortVideo/20220129/E216ACF6-DD5C-4A60-88E9-0CE9DBB79A41.mp4");
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    private void loadThumbnail(String path) {
        int count = 30;
        List<Long> extractors = new ArrayList<>();
        long videoDuration = getVideoDuration(this, path);
        long unit = videoDuration / count;
        for (int i = 0; i < count - 1; i++) {
            long time = i * unit;
            extractors.add(time);
        }
        extractors.add(videoDuration);

        SmallImageAdapter smallImageAdapter = new SmallImageAdapter(this, path, extractors);
        mRecyclerSmallImage.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        mRecyclerSmallImage.setAdapter(smallImageAdapter);
        smallImageAdapter.setOnItemClickListener(new SmallImageAdapter.OnItemClickListener() {
            @Override
            public void frame(long time) {
                Glide.with(getBaseContext()).asBitmap().load(path).frame(time*1000).into(mImgSmallImage);
            }
        });

    }

    public long getVideoDuration(Context context, String path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, getUri(path));
            String durantionStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            return Long.parseLong(durantionStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mediaMetadataRetriever.release();
        }
        return 5000;
    }

    public Uri getUri(String path) {
        Uri uri = Uri.parse(path);
        if (uri.getScheme() == null) {
            uri = Uri.fromFile(new File(path));
        }
        return uri;
    }


}