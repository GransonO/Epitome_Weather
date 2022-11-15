package com.epitome.gweather.models.remote.places.autocomplete


import com.epitome.gweather.models.remote.places.autocomplete.MainTextMatchedSubstring
import com.google.gson.annotations.SerializedName

data class StructuredFormatting(
    @SerializedName("main_text")
    val mainText: String = "",
    @SerializedName("main_text_matched_substrings")
    val mainTextMatchedSubstrings: List<MainTextMatchedSubstring> = listOf(),
    @SerializedName("secondary_text")
    val secondaryText: String = ""
)