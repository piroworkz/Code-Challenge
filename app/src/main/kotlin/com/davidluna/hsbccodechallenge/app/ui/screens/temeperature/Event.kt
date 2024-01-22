package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature

sealed interface Event {
    data object RandomTemp : Event
    data object ResetError : Event
    data object ResetData : Event
}