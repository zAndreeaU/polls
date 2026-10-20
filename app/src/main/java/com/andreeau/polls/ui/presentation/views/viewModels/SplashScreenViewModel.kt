package com.andreeau.polls.ui.presentation.views.viewModels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses.SplashScreenVMDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(): ViewModel() {
    private val _splashScreenState = MutableStateFlow(SplashScreenVMDataClass())
    val splashScreenState: StateFlow<SplashScreenVMDataClass> = _splashScreenState



    fun checkNetworkStatus(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities =
            network?.let {
                connectivityManager.getNetworkCapabilities(it)
            }

        val isOnline = capabilities?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        ) == true && capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )

        _splashScreenState.update { it.copy(isOnline = isOnline) }

    }
}