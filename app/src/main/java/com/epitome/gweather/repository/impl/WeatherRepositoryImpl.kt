package com.epitome.gweather.repository.impl

import com.epitome.gweather.repository.WeatherRepository
import com.epitome.gweather.repository.remoteUtils.Remote
import com.epitome.gweather.services.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
): WeatherRepository, Remote() {

    override suspend fun getWeatherForecast( lat: Double, lon :Double, field: String, key: String) = makeRemoteCall {
        weatherService.getTimelinesForecast(
            location = "$lat,$lon",
            apikey = key
        )
    }

}