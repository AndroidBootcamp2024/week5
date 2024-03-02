package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.network.model.Country
import com.kodeco.android.countryinfo.network.model.CountryFlags
import com.kodeco.android.countryinfo.network.model.CountryName

@Composable
fun CountryInfoList(countries: List<Country>, onCountryClick: (Country) -> Unit ) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(countries) { country ->
                CountryInfoRow(country = country, onCountryClick)
            }
        }
    }
}

@Preview
@Composable
fun CountryInfoListPreview() {
    CountryInfoList(
        listOf(
            Country(
                CountryName("Guatemala", "Guatemala"),
                listOf("Guatemala"),
                18000000.toDouble(),
                4322.toDouble(),
                CountryFlags("","","")
            ),
            Country(
                CountryName("El Salvador", "El Salvador"),
                listOf("San Salvador"),
                7000000.toDouble(),
                78934.toDouble(),
                CountryFlags("","","")
            )
        ),
        {}
    )
}
