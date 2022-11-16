package com.epitome.gweather.repositories

import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import com.epitome.gweather.models.remote.places.autocomplete.AutoComplete
import com.epitome.gweather.models.remote.places.autocomplete.MatchedSubstring
import com.epitome.gweather.models.remote.places.autocomplete.Prediction
import com.epitome.gweather.models.remote.places.autocomplete.StructuredFormatting
import com.epitome.gweather.models.remote.places.details.Geometry
import com.epitome.gweather.models.remote.places.details.Location
import com.epitome.gweather.models.remote.places.details.PlaceDetails
import com.epitome.gweather.models.remote.places.details.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaceRepositoryTest: PlaceRepository {

    private var placeRequest = AutoComplete(predictions = listOf(
            Prediction(
                description = "Test Place 1",
                matchedSubstrings = listOf(
                    MatchedSubstring(
                        5,2
                    ),
                    MatchedSubstring(
                        5,3
                    ),
                    MatchedSubstring(
                        5,4
                    ),
                ),
                placeId = "testPlaceId1",
                reference = "1234",
                structuredFormatting = StructuredFormatting(),
            ),
            Prediction(
                description = "Test Place 2",
                matchedSubstrings = listOf(
                    MatchedSubstring(
                        5,2
                    ),
                    MatchedSubstring(
                        5,3
                    ),
                    MatchedSubstring(
                        5,4
                    ),
                ),
                placeId = "testPlaceId2",
                reference = "1234",
                structuredFormatting = StructuredFormatting(),
            ),
            Prediction(
                description = "Test Place 3",
                matchedSubstrings = listOf(
                    MatchedSubstring(
                        5,2
                    ),
                    MatchedSubstring(
                        5,3
                    ),
                    MatchedSubstring(
                        5,4
                    ),
                ),
                placeId = "testPlaceId3",
                reference = "1234",
                structuredFormatting = StructuredFormatting(),
            ),
        ))

    override suspend fun suggestPlace(name: String, key: String): Flow<Resource<AutoComplete>> = flow {
        val placeFilter = placeRequest.predictions.filter { it.description.contains(name, ignoreCase = true) }
        println("Request got here --> $placeFilter")
        emit(Resource.Success(
            AutoComplete(
                predictions = placeFilter
            )
        ))
    }

    override suspend fun getPlace(placeId: String, key: String): Flow<Resource<PlaceDetails>> = flow {
        try {
            val placeItem = placeRequest.predictions.filter { it.placeId == placeId }[0]
            emit(Resource.Success(
                PlaceDetails(
                    result = Result(
                        placeId = placeId,
                        adrAddress = placeItem.description,
                        geometry = Geometry(
                            location = Location(0.0, 0.0)
                        ),
                        formattedAddress = placeItem.description,
                        name = placeItem.description
                    )
                )
            ))
        } catch (e: Exception){
            emit(Resource.Error("No place"))
        }
    }

}