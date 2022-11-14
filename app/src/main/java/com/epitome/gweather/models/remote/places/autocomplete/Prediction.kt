package com.granson.dvtweather.data.models.places.autocomplete


import com.google.gson.annotations.SerializedName

data class Prediction(
    @SerializedName("description")
    val description: String,
    @SerializedName("matched_substrings")
    val matchedSubstrings: List<MatchedSubstring>,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("reference")
    val reference: String,
    @SerializedName("structured_formatting")
    val structuredFormatting: StructuredFormatting,
    @SerializedName("terms")
    val terms: List<Term> = listOf(),
    @SerializedName("types")
    val types: List<String> = listOf()
)