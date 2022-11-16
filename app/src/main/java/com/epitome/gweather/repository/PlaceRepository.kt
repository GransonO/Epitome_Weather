package com.epitome.gweather.repository

import com.epitome.gweather.repository.remoteUtils.Resource
import com.epitome.gweather.models.remote.places.autocomplete.AutoComplete
import com.epitome.gweather.models.remote.places.details.PlaceDetails
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    suspend fun getPlace(placeId: String, key: String): Flow<Resource<PlaceDetails>>

    suspend fun suggestPlace(name: String, key: String): Flow<Resource<AutoComplete>>
}