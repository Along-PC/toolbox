package com.dragon.toolbox.exo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dragon.toolbox.R;
import com.dragon.toolbox.thumbnail.GlideEngine;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.util.List;

public class ExoActivity extends AppCompatActivity {

    private Button mBtChoose;
    private PlayerView mVideoView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);
        mBtChoose = (Button) findViewById(R.id.bt_choose);
        mVideoView = (PlayerView) findViewById(R.id.video_view);

        mBtChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        PermissionX
                .init(this)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            chooseVideo();
                        }
                    }
                });
    }

    private void chooseVideo() {
        PictureSelector
                .create(this)
                .openGallery(PictureMimeType.ofVideo())
                .imageEngine(GlideEngine.createGlideEngine())
                .isPageStrategy(true, 30, true)
                .maxSelectNum(1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        if (result==null || result.size()==0) {
                            return;
                        }
                        initializePlayer(result.get(0));
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    private void initializePlayer(LocalMedia localMedia) {
        String path = localMedia.getPath();
        Uri parse = Uri.parse(path);
        if (parse.getScheme()==null) {
            parse=Uri.fromFile(new File(path));
        }
        player = new SimpleExoPlayer.Builder(this).build();
        mVideoView.setPlayer(player);
        player.setPlayWhenReady(true);
        MediaSource mediaSource = buildMediaSource(parse);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, "com.example.exoplayerdemo");
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        return videoSource;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}