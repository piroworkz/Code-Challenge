package com.davidluna.hsbccodechallenge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.TemperatureScreen
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.TemperatureViewModel
import com.davidluna.hsbccodechallenge.app.ui.theme.HSBCCodeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TemperatureViewModel = viewModel()
            val state by viewModel.state.collectAsState()
            HSBCCodeChallengeTheme {
                TemperatureScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}