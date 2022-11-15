package com.epitome.gweather.repositories

import com.epitome.gweather.models.remote.weather.Timelines
import com.epitome.gweather.repository.WeatherRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryTest : WeatherRepository {

    override suspend fun getWeatherForecast(
        lat: Double,
        lon: Double,
        field: String,
        key: String
    ): Flow<Resource<Timelines>> = flow {
        delay(1000)
        emit(Resource.Success(
            Timelines()
        )
        )
    }

}