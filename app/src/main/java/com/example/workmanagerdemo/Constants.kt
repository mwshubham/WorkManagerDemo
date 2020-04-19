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

@file:JvmName("Constants")

package com.example.workmanagerdemo

// Notification Channel constants

// Name of Notification Channel for verbose notifications of background work
@JvmField
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"

@JvmField
val NOTIFICATION_TITLE: CharSequence = "Work Manager Demo"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

val imageList = mutableListOf<String>().apply {
    // Flower
    add("https://cdn.pixabay.com/photo/2020/04/03/15/27/flower-meadow-4999277_960_720.jpg")
    add("https://image.shutterstock.com/image-photo/field-cosmos-flower-600w-1011699703.jpg")
    add("https://image.shutterstock.com/image-photo/vintage-background-little-flowers-nature-600w-1064827904.jpg")
    add("https://images.unsplash.com/photo-1516205651411-aef33a44f7c2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=80")
    add("https://images.unsplash.com/photo-1490750967868-88aa4486c946?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80")

    // Building
    add("https://cdn.pixabay.com/photo/2020/02/14/10/52/frankfurt-4848202_960_720.jpg")
    add("https://cdn.pixabay.com/photo/2020/02/19/11/01/city-4861938_960_720.jpg")
    add("https://image.shutterstock.com/image-photo/office-building-top-view-background-600w-1011269995.jpg")
    add("https://images.unsplash.com/photo-1508062878650-88b52897f298?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60")

    // Graffiti
    add("https://images.unsplash.com/photo-1533122250115-6bb28e9a48c3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=375&q=80")
    add("https://cdn.pixabay.com/photo/2017/09/03/16/27/paint-2710975_960_720.jpg")
    add("https://cdn.pixabay.com/photo/2018/08/19/15/36/image-3616906_960_720.jpg")
}