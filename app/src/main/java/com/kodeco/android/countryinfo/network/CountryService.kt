package com.kodeco.android.countryinfo.network

import com.kodeco.android.countryinfo.network.model.Country
import retrofit2.http.GET

interface CountryService {
    @GET("/v3.1/all")
    suspend fun getAllCountries(): List<Country>
}