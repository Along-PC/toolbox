package com.dragon.download.core

class DownloadRequest private constructor(
    //下载地址
    internal val url: String,
    //保存路径
    internal var outputDir: String="",
    //是否需要状态栏显示下载进度
    internal val needNotify: Boolean = false
) {

    class DownloadRequestBuilder(val url: String) {

        //保存路径
        private var outputDir: String = ""

        //是否需要状态栏显示下载进度
        private var needNotify: Boolean = false

        fun setSavePath(dir: String): DownloadRequestBuilder {
            outputDir = dir
            return this
        }

        fun setNeedNotify(notify: Boolean): DownloadRequestBuilder {
            needNotify = notify;
            return this
        }

        fun build(): DownloadRequest {
            return DownloadRequest(url, outputDir, needNotify)
        }

    }

}