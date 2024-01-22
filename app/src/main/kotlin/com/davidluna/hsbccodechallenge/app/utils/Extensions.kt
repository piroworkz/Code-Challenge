package com.davidluna.hsbccodechallenge.app.utils

import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.hsbccodechallenge.domain.AppError
import java.io.FileNotFoundException
import java.io.IOException

suspend fun <T> suspendTryCatch(block: suspend () -> T): Either<AppError, T> = try {
    block().right()
} catch (e: Exception) {
    e.toAppError().left()
}

fun Throwable.toAppError(): AppError = when (this) {
    is FileNotFoundException -> AppError.IoError("File not found. Please contact support.")
    is IOException -> AppError.IoError("Could not get response, please try again later.")
    else -> AppError.UnknownError
}

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}