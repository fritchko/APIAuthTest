package com.example.apiauthtest.data.remote


import com.google.gson.annotations.SerializedName

data class ConditionRemote(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("text")
    val text: String?
)