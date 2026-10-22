package com.andreeau.polls.ui.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andreeau.pollcore.utils.NetworkMonitor
import com.andreeau.polls.ui.presentation.navigation.PollsNavigation
import com.andreeau.polls.ui.theme.PollsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PollsAppTheme() {

                PollsNavigation(  networkMonitor = NetworkMonitor(applicationContext))

            }
        }
    }
}