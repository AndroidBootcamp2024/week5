package com.kodeco.android.countryinfo.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.network.RetrofitInstance
import com.kodeco.android.countryinfo.network.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CountryInfoScreen(countries: List<Country>, selectedCountry: Country?, onCountrySelected: (Country) -> Unit, resetCountrySelected: () -> Unit) {

    if (selectedCountry == null ) {
        CountryInfoList(
            countries = countries,
            onCountryClick = { onCountrySelected(it) }
        )
    } else {
        selectedCountry?.let { CountryDetailsScreen(country = it, onReturn = resetCountrySelected) }
    }
}

@Preview
@Composable
fun CountryInfoScreenPreview() {
    //CountryInfoScreen(Country())
}
