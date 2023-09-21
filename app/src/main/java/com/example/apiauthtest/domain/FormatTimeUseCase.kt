package com.example.apiauthtest.domain

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

fun String?.formatToLocalTime(): String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        return try{
            val date = inputFormat.parse(this)
            outputFormat.format(date)
        } catch (e: Exception){
            "N/A"
        }
    }