package com.epitome.gweather.services

import com.granson.dvtweather.data.models.places.autocomplete.AutoComplete
import com.granson.dvtweather.data.models.places.details.PlaceDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("/maps/api/place/autocomplete/json?")
    suspend fun placeSearch(
        @Query("input") name: String,
        @Query("key") apiKey: String,
    ): AutoComplete

    @GET("/maps/api/place/details/json?")
    suspend fun getPlaceDetails(
        @Query("placeid") placeId: String,
        @Query("key") apiKey: String,
    ): PlaceDetails

}