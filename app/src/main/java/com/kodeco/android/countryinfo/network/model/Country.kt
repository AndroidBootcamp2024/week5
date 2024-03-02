package com.kodeco.android.countryinfo.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country (
    @Json(name = "name") val name: CountryName,
    @Json(name = "capital") val capital: List<String>?,
    @Json(name= "population") val population: Double?,
    @Json(name= "area") val area: Double?,
    @Json(name = "flags") val flags: CountryFlags
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CountryName (
    @Json(name = "common") val common: String?,
    @Json(name = "official") val official: String?
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CountryFlags (
    @Json(name = "png") val png: String?,
    @Json(name = "svg") val svg: String?,
    @Json(name = "alt") val alt: String?
): Parcelable