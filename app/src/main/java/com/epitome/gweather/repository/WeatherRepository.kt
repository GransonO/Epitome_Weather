package com.epitome.gweather.repository

import com.epitome.gweather.models.remote.weather.Timelines
import com.epitome.gweather.repository.remoteUtils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherForecast( lat: Double, lon :Double, field: String, key: String): Flow<Resource<Timelines>>
}