package com.kodeco.android.countryinfo.network

import androidx.core.content.ContextCompat.getSystemService
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    val moshi = Moshi.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: CountryService = retrofit.create(CountryService::class.java)
}