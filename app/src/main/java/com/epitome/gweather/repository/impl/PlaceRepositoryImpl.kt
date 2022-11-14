package com.epitome.gweather.repository.impl

import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.remoteUtils.Remote
import com.epitome.gweather.repository.remoteUtils.Resource
import com.epitome.gweather.services.PlaceService
import com.granson.dvtweather.data.models.places.autocomplete.AutoComplete
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeService: PlaceService
): PlaceRepository, Remote() {

    override suspend fun getPlace(placeId: String, key: String) = makeRemoteCall {
        placeService.getPlaceDetails(
            placeId = placeId,
            apiKey = key
        )
    }

    override suspend fun suggestPlace(name: String, key: String): Flow<Resource<AutoComplete>> = makeRemoteCall {
        placeService.placeSearch(
            name = name, apiKey = key
        )
    }

}