package com.epitome.gweather.repositories

import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import com.epitome.gweather.models.remote.places.autocomplete.AutoComplete
import com.epitome.gweather.models.remote.places.details.PlaceDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaceRepositoryTest: PlaceRepository {

    override suspend fun getPlace(placeId: String, key: String): Flow<Resource<PlaceDetails>> = flow {
        delay(1000)
        emit(Resource.Success(myData = PlaceDetails()))
    }

    override suspend fun suggestPlace(name: String, key: String): Flow<Resource<AutoComplete>> = flow {
        delay(1000)
        emit(Resource.Success(myData = AutoComplete()))
    }

}