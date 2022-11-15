package com.granson.dvtweather.data.models.places.details


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address_components")
    val addressComponents: List<AddressComponent> = listOf(),
    @SerializedName("adr_address")
    val adrAddress: String,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("icon_background_color")
    val iconBackgroundColor: String = "",
    @SerializedName("icon_mask_base_uri")
    val iconMaskBaseUri: String = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("photos")
    val photos: List<Photo>  = listOf(),
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("reference")
    val reference: String = "",
    @SerializedName("types")
    val types: List<String> = listOf(),
    @SerializedName("url")
    val url: String = "",
    @SerializedName("utc_offset")
    val utcOffset: Int = 0,
    @SerializedName("vicinity")
    val vicinity: String = "",
    @SerializedName("website")
    val website: String = ""
)