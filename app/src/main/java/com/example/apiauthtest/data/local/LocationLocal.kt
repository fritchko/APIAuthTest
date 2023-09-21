package com.example.apiauthtest.data.local

import com.google.gson.annotations.SerializedName

data class LocationLocal(
    val country: String?,
    val lat: Double?,
    val localtime: String?,
    val localtimeEpoch: Int?,
    val lon: Double?,
    val name: String?,
    val region: String?,
    val tzId: String?
)
