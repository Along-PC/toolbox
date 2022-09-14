package com.dragon.download.utils;

import static android.os.Environment.MEDIA_MOUNTED;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {

    /**
     * @param context 上下文对象
     * @param dir     存储目录
     * @return
     */
    public static String getFilePath(Context context, String dir) {
        return getFilePath(context, dir, true);
    }

    /**
     *
     * @param context   上下文对象
     * @param dir       存储目录
     * @param createDir 是否创建父文件夹
     * @return
     */
    public static String getFilePath(Context context, String dir, Boolean createDir) {
        String directoryPath = "";
        //判断SD卡是否装载
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            directoryPath = context.getExternalFilesDir("").getAbsolutePath();
        } else {
            //没装载内存卡就存机身内存
            directoryPath = context.getFilesDir().getAbsolutePath();
        }
        directoryPath = directoryPath + File.separator + dir;
        if (createDir) {
            File file = new File(directoryPath);
            //判断文件目录是否存在
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return directoryPath;
    }

    /**
     * 遍历删除文件夹下所有内容
     * @param path
     */
    public static void clearDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] files = file.listFiles();
        for (File children : files) {
            clearChildren(children);
        }
        file.delete();
    }

    /**
     * 删除子文件
     * @param file
     */
    private static void clearChildren(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (File children : files) {
                clearChildren(children);
            }
            file.delete();
        }
    }

}
