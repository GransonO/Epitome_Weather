package com.epitome.gweather.di

import android.content.Context
import com.epitome.gweather.services.PlaceService
import com.epitome.gweather.services.WeatherService
import com.epitome.gweather.services.Constants.PLACE_URL
import com.epitome.gweather.services.Constants.WEATHER_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun retroLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val authRequest = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .build()

                return@addInterceptor chain.proceed(authRequest)
            }
            .addNetworkInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(okHttpClient: OkHttpClient): WeatherService {
        val retrofit = Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun providesPlaceService(okHttpClient: OkHttpClient): PlaceService {
        val retrofit = Retrofit.Builder()
            .baseUrl(PLACE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(PlaceService::class.java)
    }
}