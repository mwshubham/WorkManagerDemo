package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanagerdemo.DownloadImageWorker.Companion.KEY_IMAGE_URL
import kotlin.random.Random

class ImagePathGeneratorWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {
        const val KEY_SELECTED_INDEX = "selected_index"
        val TAG: String = ImagePathGeneratorWorker::class.java.simpleName
    }

    override fun doWork(): Result {
        makeStatusNotification(applicationContext, "Generating Random Image URL.")
        sleep()
        val index = inputData.getInt(KEY_SELECTED_INDEX, 0)
        Log.i(TAG, "index: $index")
        val imageUrl = when (inputData.getInt(KEY_SELECTED_INDEX, 0)) {
            1 -> imageList[Random.nextInt(0, 5)]
            2 -> imageList[Random.nextInt(5, 9)]
            3 -> imageList[Random.nextInt(9, imageList.size)]
            else -> imageList[Random.nextInt(0, imageList.size)]
        }
        Log.i(TAG, "imageUrl: $imageUrl")
        val output = workDataOf(KEY_IMAGE_URL to imageUrl)
        makeStatusNotification(applicationContext, "Image URL Generated.")
        return Result.success(output)
    }

}