package com.epitome.gweather.repository.remoteUtils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class Remote {

    suspend fun <T>makeRemoteCall(apiCall: suspend () -> T): Flow<Resource<T>>{
        return flow {
            try {
                emit(Resource.Loading(true))
                val data = withContext(Dispatchers.IO){
                    apiCall.invoke()
                }
                emit(Resource.Success(myData = data))
                emit(Resource.Loading(false))
            } catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error(myMessage = e.message))
                emit(Resource.Loading(false))
            }
        }
    }
}