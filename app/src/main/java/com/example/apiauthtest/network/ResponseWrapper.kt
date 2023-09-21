package com.example.apiauthtest.network

sealed class ResponseWrapper<out T> {
    data object Loading : ResponseWrapper<Nothing>()

    data class Success<T>(val value: T?) : ResponseWrapper<T>()

    data class Error(
        val message: String,
        val code: Int? = null,
    ) : ResponseWrapper<Nothing>()
}