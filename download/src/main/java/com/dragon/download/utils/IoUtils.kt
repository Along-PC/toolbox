package com.dragon.download.utils

import java.io.Closeable

object IoUtils {

    fun close(closeable: Closeable?){
        closeable?.let {
            it.close()
        }
    }

}