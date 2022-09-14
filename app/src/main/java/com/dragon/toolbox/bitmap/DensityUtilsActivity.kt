package com.dragon.toolbox.bitmap

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dragon.toolbox.utils.IoUtils
import com.dragon.toolbox.databinding.ActivityDensityUtilsBinding
import com.dragon.toolbox.utils.loge
import java.io.FileOutputStream


class DensityUtilsActivity : AppCompatActivity() {

    val tag = "DensityUtilsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDensityUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.post {
            supportActionBar?.let {
                Log.d(tag, "actionbarHeight:${it.height}")
            }
        }

        val ofFloat =
            ObjectAnimator.ofFloat(binding.btActivityDesnityInfo, "translationX", 2000f, 0f)
        val duration = ofFloat.duration
        Log.d(tag,"-------default :$duration")

        val statusHeight = getStatusHeight()
        Log.d(tag, "statusHeight:${statusHeight}")

        binding.btActivityDesnityCreate.setOnClickListener {
            val inputPath = getExternalFilesDir("")?.absolutePath + "/1.jpg"
            inputPath?.let { input ->
                val options = BitmapFactory.Options()
                options.inDensity = 300
                options.inSampleSize = 1
                val decodeFile = BitmapFactory.decodeFile(input, options)
                val s = getExternalFilesDir("")?.absolutePath + "/create.jpg"
                s.let {
                    val outputStream = FileOutputStream(s)
                    decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    IoUtils.close(outputStream)
                }
            }
        }

        binding.btActivityDesnitySave.setOnClickListener {
            val inputPath = getExternalFilesDir("")?.absolutePath + "/1.jpg"
            inputPath?.let { input ->
                val decodeFile = BitmapFactory.decodeFile(input)
                val s = getExternalFilesDir("")?.absolutePath + "/density.jpg"
                s.let {
//                    saveBitmapToJpg(decodeFile, File(s), 230)
                }
            }
        }

        binding.btActivityDesnityInfo.setOnClickListener {
            val inputPath = getExternalFilesDir("")?.absolutePath + "/density.jpg"
            var exifInterface = ExifInterface(inputPath)
            "x:${exifInterface.getAttribute(ExifInterface.TAG_Y_RESOLUTION)}"?.loge()
            "y:${exifInterface.getAttribute(ExifInterface.TAG_X_RESOLUTION)}"?.loge()
        }

    }

    /**
     * 获取状态栏高度
     * @return
     */
    protected fun getStatusHeight(): Int {
        val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}