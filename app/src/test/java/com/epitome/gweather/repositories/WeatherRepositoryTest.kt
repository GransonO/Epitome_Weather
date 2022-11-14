package com.epitome.gweather.repositories

import com.epitome.gweather.repository.WeatherRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class WeatherRepositoryTest : WeatherRepository {

    override suspend fun getWeatherForecast() = flow {
        delay(1000)
        emit(Resource.Success(myData = ""))
    }

}