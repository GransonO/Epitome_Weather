package com.granson.dvtweather.data.models.places.autocomplete


import com.google.gson.annotations.SerializedName

data class StructuredFormatting(
    @SerializedName("main_text")
    val mainText: String = "",
    @SerializedName("main_text_matched_substrings")
    val mainTextMatchedSubstrings: List<MainTextMatchedSubstring> = listOf(),
    @SerializedName("secondary_text")
    val secondaryText: String = ""
)