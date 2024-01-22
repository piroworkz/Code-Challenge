package com.davidluna.hsbccodechallenge.mocks

import com.davidluna.hsbccodechallenge.data.model.Temperature
import com.davidluna.hsbccodechallenge.data.model.TemperaturesFile
import com.davidluna.hsbccodechallenge.domain.Data


val temperatureListMock = (0..5).map {
    Data(
        place = "Place $it",
        unit = "C",
        value = it
    )
}

val tempFile = TemperaturesFile(
    temperature = Temperature(
        results = temperatureListMock,
        recordTime = "10:00:00"
    )
)