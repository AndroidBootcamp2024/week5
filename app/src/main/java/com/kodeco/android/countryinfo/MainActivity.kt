package com.kodeco.android.countryinfo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.ContextCompat
import com.kodeco.android.countryinfo.network.RetrofitInstance
import com.kodeco.android.countryinfo.network.model.Country
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.components.Loading
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val countryState: MutableState<CountryInfoState> = rememberSaveable { mutableStateOf(CountryInfoState.LoadingCountries("Loading...")) }

            LaunchedEffect(true) {
                if (hasInternetConnection()) {
                        try {
                            withContext(Dispatchers.IO) {
                                val response = RetrofitInstance.api.getAllCountries()
                                countryState.value = CountryInfoState.LoadedCountries(response)
                            }
                        } catch (e: Exception) {
                            Log.d("Text", "${e.message}")
                            countryState.value = CountryInfoState.Error(e.message ?: "")
                        }
                } else {
                    countryState.value = CountryInfoState.Error("No internet connection.")
                }
            }
            MyApplicationTheme {
                val state = countryState.value
                when (state) {
                    is CountryInfoState.LoadingCountries -> Loading(state.loadingMessage)
                    is CountryInfoState.LoadedCountries  -> {
                        CountryInfoScreen(
                            countries = state.countries,
                            selectedCountry = null,
                            onCountrySelected = { countryState.value = CountryInfoState.ShowOneCountry(state.countries, state.countries.indexOf(it)) },
                            resetCountrySelected = { countryState.value = CountryInfoState.LoadedCountries(state.countries) })
                    }
                    is CountryInfoState.ShowOneCountry ->
                        CountryInfoScreen(
                            countries = state.countries,
                            selectedCountry = state.countries[state.pos],
                            onCountrySelected = { countryState.value = CountryInfoState.ShowOneCountry(state.countries, state.countries.indexOf(it)) },
                            resetCountrySelected = { countryState.value = CountryInfoState.LoadedCountries(state.countries) })
                    is CountryInfoState.Error -> CountryErrorScreen(state.errorMessage)
                }
            }
        }
    }

    fun hasInternetConnection(): Boolean {
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}

@Parcelize
sealed class CountryInfoState : Parcelable {
    data class LoadingCountries(val loadingMessage: String): CountryInfoState()
    data class LoadedCountries(val countries: List<Country>): CountryInfoState()
    data class ShowOneCountry(val countries: List<Country>, val pos: Int): CountryInfoState()

    data class Error(val errorMessage: String): CountryInfoState()
}
