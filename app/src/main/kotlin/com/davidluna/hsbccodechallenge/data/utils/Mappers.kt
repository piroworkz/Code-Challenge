package com.davidluna.hsbccodechallenge.data.utils

import com.davidluna.hsbccodechallenge.data.model.TemperaturesFile
import com.davidluna.hsbccodechallenge.domain.Data
import com.google.gson.Gson

fun String.toDomain(): List<Data> {
    return Gson().fromJson<TemperaturesFile>(this, TemperaturesFile::class.java).temperature.results
}