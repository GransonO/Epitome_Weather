package com.epitome.gweather.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitome.gweather.models.local.VMState
import com.epitome.gweather.repository.PlaceRepository
import com.epitome.gweather.repository.WeatherRepository
import com.epitome.gweather.repository.remoteUtils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _suggestion = MutableSharedFlow<VMState>()
    val suggestion = _suggestion.asSharedFlow()
    fun suggestPlace(place: String, key: String) = viewModelScope.launch {
        placeRepository.suggestPlace(
            name = place,
            key = key
        ).collect{
            when(it){
                is Resource.Success -> {
                    _suggestion.emit(
                        VMState(
                            data = it.data
                        )
                    )
                }
                is Resource.Error -> {
                    _suggestion.emit(
                        VMState(
                            error = it.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _suggestion.emit(
                        VMState(
                            loading = it.isLoading
                        )
                    )
                }
            }
        }
    }

    private val _place = MutableSharedFlow<VMState>()
    val place = _place.asSharedFlow()
    fun getPlaceDetails(id: String, key: String,) = viewModelScope.launch {
        placeRepository.getPlace(
            placeId = id,
            key = key
        ).collect{
            when(it){
                is Resource.Success -> {
                    _place.emit(
                        VMState(
                            data = it.data
                        )
                    )
                }
                is Resource.Error -> {
                    _place.emit(
                        VMState(
                            error = it.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _place.emit(
                        VMState(
                            loading = it.isLoading
                        )
                    )
                }
            }
        }
    }

    private val _weather = MutableSharedFlow<VMState>()
    val weather = _weather.asSharedFlow()
    fun getWeatherForecast(key: String, lat: Double, lon :Double, field: String) = viewModelScope.launch {
        weatherRepository.getWeatherForecast(
            lat, lon, field, key
        ).collect {
            when(it){
                is Resource.Success -> {
                    _weather.emit(
                        VMState(
                            data = it.data
                        )
                    )
                }
                is Resource.Error -> {
                    _weather.emit(
                        VMState(
                            error = it.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _weather.emit(
                        VMState(
                            loading = it.isLoading
                        )
                    )
                }
            }
        }
    }
}