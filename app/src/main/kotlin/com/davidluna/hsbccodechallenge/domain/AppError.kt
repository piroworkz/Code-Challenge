package com.davidluna.hsbccodechallenge.domain

sealed class AppError(
    val description: String
) : Throwable() {

    data class IoError(override val message: String) : AppError(message)
    data object UnknownError : AppError("Something went wrong, please try again later.")
    data object NoMoreData : AppError("No more data available")

}
