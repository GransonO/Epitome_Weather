package com.epitome.gweather.services

import com.epitome.gweather.models.remote.weather.Timelines
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v4/timelines")
    fun getTimelinesForecast(
        @Query("location") location: String,
        @Query("fields") fields: String,
        @Query("apikey") apikey: String,
    ): Timelines

}