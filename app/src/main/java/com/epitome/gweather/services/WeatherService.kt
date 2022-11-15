package com.epitome.gweather.services

import com.epitome.gweather.models.remote.weather.Timelines
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v4/timelines?fields=temperature&fields=weatherCode&fields=humidity&fields=cloudCover&fields=precipitationType&fields=precipitationIntensity&fields=windSpeed&fields=windDirection&fields=visibility")
    suspend fun getTimelinesForecast(
        @Query("location") location: String,
        @Query("apikey") apikey: String,
    ): Timelines

}