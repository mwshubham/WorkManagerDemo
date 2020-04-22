/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.workmanagerdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.workmanagerdemo.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val workManager = WorkManager.getInstance()

        binding.btnStartWork.setOnClickListener {
            binding.btnStartWork.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.tvLogs.visibility = View.VISIBLE
            binding.ivMain.visibility = View.GONE

            val imagePathGeneratorWorkerInputData = Data.Builder()
                .putInt(ImagePathGeneratorWorker.KEY_SELECTED_INDEX, binding.spinner.selectedItemPosition)
                .build()

            val imagePathGenerationWork = OneTimeWorkRequest
                .Builder(ImagePathGeneratorWorker::class.java)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setInputData(imagePathGeneratorWorkerInputData)
                .build()

            val downloadImageWorkConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val downloadImageWork = OneTimeWorkRequest
                .Builder(DownloadImageWorker::class.java)
                .setConstraints(downloadImageWorkConstraints)
                .build()

            val workContinuation = workManager
                .beginUniqueWork(
                    KEY_UNIQUE_WORK_NAME,
                    ExistingWorkPolicy.REPLACE,
                    imagePathGenerationWork
                )

            workContinuation
                .then(downloadImageWork)
                .enqueue()

            workManager.getWorkInfosForUniqueWorkLiveData(KEY_UNIQUE_WORK_NAME).observe(this, workInfosObserver)
            makeStatusNotification(this, "Image Path Generation Work Enqueued with 5sec delay")
            Toast.makeText(this, "Image Path Generation Work Enqueued with 5sec delay", Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("SpellCheckingInspection")
    private val workInfosObserver: Observer<List<WorkInfo>> = Observer { listOfWorkInfo ->
        if (listOfWorkInfo.isNullOrEmpty()) {
            return@Observer
        }
        val workInfo0 = listOfWorkInfo[0]
        val workInfo1 = listOfWorkInfo[1]


        Log.i(TAG, "${workInfo0.tags} : ${workInfo0.state}")
        Log.i(TAG, "${workInfo1.tags} : ${workInfo1.state}")

        val logMessage = "${workInfo0.tags.first().toString().split(".").last()}: ${workInfo0.state} \n${workInfo1.tags.first().toString().split(".").last()}: ${workInfo1.state}"
        binding.tvLogs.text = logMessage

        if (workInfo0.state.isFinished && workInfo1.state.isFinished) {
            val outputImageUrl = workInfo1.outputData.getString(DownloadImageWorker.KEY_IMAGE_URL)
            if (!outputImageUrl.isNullOrEmpty()) {
                binding.btnStartWork.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.ivMain.visibility = View.VISIBLE
                binding.tvLogs.visibility = View.GONE

                Glide.with(this).load(outputImageUrl).into(binding.ivMain)
            }
        }
    }

    companion object {
        const val KEY_UNIQUE_WORK_NAME = "download_image_work"
        val TAG: String = MainActivity::class.java.simpleName
    }
}