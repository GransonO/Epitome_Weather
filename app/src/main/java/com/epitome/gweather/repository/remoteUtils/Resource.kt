package com.epitome.gweather.repository.remoteUtils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
){
    class Success<T>(myData: T?): Resource<T>(data = myData)
    class Error<T>(myMessage: String?): Resource<T>(message = myMessage)
    class Loading<T>(loading: Boolean): Resource<T>(isLoading = loading)
}