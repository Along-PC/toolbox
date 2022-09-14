package com.dragon.download.listener

interface DownloadListener{

    /**
     * 文件下载准备               下载地址
     * @param key String
     */
    fun onPrepare(key:String)

    /**
     * 下载进度更新
     * @param key String        下载地址
     * @param progress Int      下载进度
     * @param read Long         当前读取大小
     * @param count Long        文件总大小
     */
    fun onProgressUpdate(key:String,progress:Int,read:Long,count:Long)

    /**
     * 文件下载异常
     * @param key String        下载地址
     * @param error Throwable
     */
    fun onDownloadError(key: String,error:Throwable)

    /**
     * 文件下载成功
     * @param key String        下载地址
     * @param saveAddress String
     */
    fun onDownloadSuccess(key:String,saveAddress:String)

    /**
     * 文件下载暂停
     * @param key String        下载地址
     */
    fun onDownloadPause(key: String)

    /**
     * 文件继续暂停
     * @param key String        下载地址
     */
    fun onDownloadResume(key: String)

    /**
     * 文件下载取消
     * @param key String
     */
    fun onCancel(key: String)

}
