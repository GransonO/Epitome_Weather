package com.epitome.gweather.di

import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.WeatherRepository
import com.epitome.gweather.repository.impl.PlaceRepositoryImpl
import com.epitome.gweather.repository.impl.WeatherRepositoryImpl
import com.epitome.gweather.services.PlaceService
import com.epitome.gweather.services.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesWeatherRepository(weatherService: WeatherService): WeatherRepository = WeatherRepositoryImpl(weatherService)

    @Provides
    @Singleton
    fun providePlaceRepository(placeService: PlaceService): PlaceRepository = PlaceRepositoryImpl(placeService)

}