package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kodeco.android.countryinfo.network.model.Country
import com.kodeco.android.countryinfo.network.model.CountryFlags
import com.kodeco.android.countryinfo.network.model.CountryName

@Composable
fun CountryInfoRow(country: Country, onCountryClick: (Country) -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.secondary, RoundedCornerShape(8.dp))
            .clickable { onCountryClick(country) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            country.name.common?.let {
                Text(
                    text = "Name : $it",
                    fontSize = 18.sp
                )
            }
            country.capital?.let { capitalList ->
                if (!capitalList.isEmpty()) {
                    Text(
                        text = "Capital: ${capitalList[0]}",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CountryInfoRowPreview() {
    CountryInfoRow(
        Country(
            CountryName("Guatemala", "Guatemala"),
            listOf("Guatemala"),
            18000000.toDouble(),
            5433.toDouble(),
            CountryFlags("","","")
        ),
        {}
    )
}
