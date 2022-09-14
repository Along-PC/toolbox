package com.dragon.toolbox.bitmap;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ImageDensityUtils {

    public static void saveBitmapToJpg(Bitmap bitmap, String file, int dpi) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.close();

        byte[] bytes = getDpi(dpi);
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        accessFile.seek(13);
        accessFile.write(bytes);
        accessFile.close();

    }

    private static byte[] getDpi( int dpi) {
        byte[] imageData = new byte[5];
        imageData[0] = 1;
        imageData[1] = (byte) (dpi >> 8);
        imageData[2] = (byte) (dpi & 0xff);
        imageData[3] = (byte) (dpi >> 8);
        imageData[4] = (byte) (dpi & 0xff);
        return imageData;
    }

}

//    {
//
//        FileInputStream inputStream = new FileInputStream(theSourcePhotoFilePathName);
//        Bitmap bitmap = null;
//        BitmapRegionDecoder decoder = null;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 1;
//        options.inDensity = 300;
//        // Tried this but not working.
//        try {
//            decoder = BitmapRegionDecoder.newInstance(in, false);
//            bitmap = decoder.decodeRegion(region, options);
//            // the region has cropping coordinates.
//        } catch (IllegalArgumentException e) {
//            Log.d("First Activity", "Failed to recycle bitmap for rect=" + region, e);
//        } catch (IOException e) {
//            Log.d("First Activity", "Failed to decode into rect=" + region, e);
//        } finally {
//            if (decoder != null) decoder.recycle();
//        }
//        inputStream.close();
//        inputStream = null;
//        FileOutputStream fos = new FileOutputStream(theTargetTempFolderDestFilePath);
//        bitmap.compress(CompressFormat.JPEG, jpegCompressionRatio, fos);
//        fos.flush();
//        fos.close();
//        fos = null;
//    }
