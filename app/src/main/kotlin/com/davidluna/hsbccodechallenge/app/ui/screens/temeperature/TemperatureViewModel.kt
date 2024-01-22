package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.hsbccodechallenge.app.utils.toAppError
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data
import com.davidluna.hsbccodechallenge.usercases.GetTemperaturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val getTemperaturesUseCase: GetTemperaturesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getTemperatures()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: AppError? = null,
        val temperatures: List<Data> = emptyList(),
        val randomData: Data? = null,
        val shownData: MutableList<Data> = mutableListOf()
    )

    fun onEvent(event: Event) {
        when (event) {
            is Event.RandomTemp -> getRandomData()
            is Event.ResetError -> resetError()
            is Event.ResetData -> resetData()
        }
    }

    private fun resetData() {
        _state.update { state -> state.copy(shownData = mutableListOf()) }
    }

    private fun resetError() {
        _state.update { state -> state.copy(error = null) }
    }

    private fun getRandomData() = with(_state.value) {
        shownData.add(randomData ?: return)
        val newRandom: Data? = temperatures.filter { it !in shownData }.randomOrNull()
        if (newRandom == null) {
            _state.update { state -> state.copy(error = AppError.NoMoreData) }
        } else {
            _state.update { state -> state.copy(randomData = newRandom) }
        }
    }

    private fun getTemperatures() {
        dataDownload {
            getTemperaturesUseCase()
                .fold(
                    ifLeft = {
                        _state.update { state -> state.copy(error = it) }
                    },
                    ifRight = {
                        _state.update { state ->
                            state.copy(
                                temperatures = it,
                                randomData = it.randomOrNull()
                            )
                        }
                    }
                )
        }
    }

    private fun dataDownload(block: suspend () -> Unit) =
        viewModelScope.launch {
            try {
                _state.update { state -> state.copy(isLoading = true) }
                block()
            } catch (e: Exception) {
                _state.update { state -> state.copy(isLoading = false, error = e.toAppError()) }
            } finally {
                _state.update { state -> state.copy(isLoading = false) }
            }
        }

}


