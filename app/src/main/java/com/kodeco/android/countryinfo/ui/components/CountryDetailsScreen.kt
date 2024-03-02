package com.kodeco.android.countryinfo.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.android.countryinfo.R
import com.kodeco.android.countryinfo.network.model.Country
import com.kodeco.android.countryinfo.network.model.CountryFlags
import com.kodeco.android.countryinfo.network.model.CountryName

@Composable
fun CountryDetailsScreen(country: Country, onReturn: () -> Unit) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier= Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_return),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp,2.dp,8.dp,2.dp)
                        .width(25.dp)
                        .clickable {
                            onReturn()
                        }
                )
                country.name.common?.let {
                    Text(
                        text= it,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(10.dp, 0.dp,0.dp, 0.dp))
                }
            }
            Box(
                modifier = Modifier
                    .padding(16.dp, 8.dp, 16.dp, 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column {
                    country.capital?.let {
                        if (!it.isEmpty()) {
                            Text(
                                text="Capital: ${it[0]}",
                                fontSize = 22.sp
                            )
                        }
                    }
                    country.population?.let {
                        Text(
                            text= "Population : $it",
                            fontSize = 22.sp
                        )
                    }
                    country.area?.let {
                        Text(
                            text= "Area: $it",
                            fontSize= 22.sp
                        )
                    }
                    country.flags.png?.let {url ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp, 100.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CountryDetailsScreenPreview() {
   CountryDetailsScreen(
       Country(
           CountryName("Guatemala", "Guatemala"),
           listOf("Guatemala"),
           18000000.toDouble(),
           5003.toDouble(),
           CountryFlags("https://flagcdn.com/w320/cy.png","https://flagcdn.com/w320/cy.png","https://flagcdn.com/w320/cy.png")
       ),
       {}
   )
}
