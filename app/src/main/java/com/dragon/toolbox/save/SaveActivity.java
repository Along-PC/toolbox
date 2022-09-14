package com.dragon.toolbox.save;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dragon.toolbox.utils.IoUtils;
import com.dragon.toolbox.R;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class SaveActivity extends AppCompatActivity {

    private static final String TAG = "SaveActivity";

    private ImageView mImgShow;
    private Button mBtLoad;
    private Button mBtSave;
    private Button mBtExplorer;
    private Button mBtData;

    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        mImgShow = (ImageView) findViewById(R.id.img_show);
        mBtLoad = (Button) findViewById(R.id.bt_load);
        mBtSave = (Button) findViewById(R.id.bt_save);
        mBtExplorer = (Button) findViewById(R.id.bt_explorer);
        mBtData = (Button) findViewById(R.id.bt_data);

        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.e(TAG,"data:"+data);

        mBtLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = getAssetBitmap(SaveActivity.this, "temp.jpg");
                mImgShow.setImageBitmap(bitmap);
            }
        });

        mBtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionX
                        .init(SaveActivity.this)
                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new RequestCallback() {
                            @Override
                            public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                                if (allGranted) {
                                    saveBitmapToAlbum(SaveActivity.this, "sys.png", "image/png", bitmap);
//                                    String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/original.png";
//                                    saveBitmap(imagePath,bitmap, Bitmap.CompressFormat.PNG,100,true,true);
                                }
                            }
                        });
            }
        });
        mBtExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/gif");
                startActivityForResult(intent,10086);
            }
        });
        mBtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction("com.dragon.http");
                intent1.setData(Uri.parse("content://com.android.providers.media.documents/document/image%3A11286"));
//                intent1.setData(Uri.parse("http://www.baidu2.com/hah"));
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10086 && resultCode==RESULT_OK) {
            Uri uri = data.getData();
            Log.e(TAG,"uri:"+uri.toString());
        }
    }

    public Bitmap getAssetBitmap(Context context, String name) {
        AssetManager assets = context.getAssets();
        InputStream open = null;
        try {
            open = assets.open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(open);
        return bitmap;
    }

    public String saveBitmapToAlbum(Context context, String imageName, String mimeType, Bitmap bitmap) {
        ContentValues values = new ContentValues();
        //图片名称    ----三个必须参数
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName);
        //图片mime_type   ----三个必须参数
        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        //图片存储的路径   ----三个必须参数
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //RELATIVE_PATH常量，表示文件存储的相对路径，可选值有DIRECTORY_DCIM、DIRECTORY_PICTURES、
            // DIRECTORY_MOVIES、DIRECTORY_MUSIC等，分别表示相册、图片、电影、音乐等目录。
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);
        } else {
            //而在之前的系统版本中并没有RELATIVE_PATH，所以要使用DATA常量（已在Android 10中废弃），
            // 并拼装出一个文件存储的绝对路径才行
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            values.put(MediaStore.MediaColumns.DATA, rootPath + "/" + Environment.DIRECTORY_DCIM + "/" + imageName);
        }
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (uri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = context.getContentResolver().openOutputStream(uri);
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                IoUtils.INSTANCE.close(outputStream);
            }
        }
        return uri == null ? null : uri.toString();
    }

    public boolean saveBitmap(String imagePath, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality,
                                     boolean isInsert, boolean isNotify) {
        File file = new File(imagePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, quality, fileOutputStream);

            if (isInsert) {
                insertSystemPhotos(file);
            }

            if (isNotify) {
                refreshPictures(file);
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            IoUtils.INSTANCE.close(fileOutputStream);
        }
    }

    // 其次把文件插入到系统图库
    private void insertSystemPhotos(File file) {
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 最后通知图库更新
    private void refreshPictures(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }
}