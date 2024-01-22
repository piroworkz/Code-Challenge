package com.davidluna.hsbccodechallenge.data.model

import com.davidluna.hsbccodechallenge.domain.Data
import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("data")
    val results: List<Data>,
    val recordTime: String
)