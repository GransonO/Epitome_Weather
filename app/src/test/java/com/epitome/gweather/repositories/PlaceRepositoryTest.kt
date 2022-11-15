package com.epitome.gweather.repositories

import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaceRepositoryTest: PlaceRepository {

    override suspend fun getPlace(): Flow<Resource<String>> = flow {
        delay(1000)
        emit(Resource.Success(myData = ""))
    }

}