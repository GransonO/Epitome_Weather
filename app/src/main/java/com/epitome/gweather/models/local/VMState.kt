package com.epitome.gweather.models.local

data class VMState(
    val data: Any? = null,
    val error: String? = null,
    val loading: Boolean = false
)
