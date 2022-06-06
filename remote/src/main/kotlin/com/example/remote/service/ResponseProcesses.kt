package com.example.remote.service

import arrow.core.Either
import arrow.core.Option
import arrow.core.left
import arrow.core.rightIfNotNull
import com.example.common.EMPTY_STRING
import com.example.data.error.*
import com.example.remote.dto.response.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection

internal suspend fun <T : Any> executeNetworkRequest(f: suspend () -> Response<T>): Either<RemoteDataError, T> {
    return Either.catch(f).fold(
        ifLeft = { error ->
            UnrecognizedRemoteError(error.localizedMessage ?: error.toString()).left()
        },
        ifRight = {
            processResponse(it)
        }
    )
}

internal suspend fun <T : Any> processResponse(response: Response<T>): Either<RemoteDataError, T> {
    return if (response.isSuccessful) {
        response.body().rightIfNotNull<RemoteDataError, T> { UnrecognizedRemoteError() }
    } else {
        val error = checkErrorResponse(response.errorBody()).fold(
            {
                when (response.code()) {
                    HttpURLConnection.HTTP_BAD_REQUEST -> InvalidRequest
                    HttpURLConnection.HTTP_UNAUTHORIZED -> InvalidCredentials
                    HttpURLConnection.HTTP_NOT_FOUND -> NotFound
                    else -> UnrecognizedRemoteError()
                }
            },
            {
                UnrecognizedRemoteError(it.statusMessage)
            }
        )

        error.left()
    }
}

private suspend fun checkErrorResponse(body: ResponseBody?): Option<ErrorResponse> = Either.catch {
    Moshi.Builder().build().adapter(ErrorResponse::class.java).fromJson(
        body?.string() ?: EMPTY_STRING) ?: ErrorResponse(EMPTY_STRING, -1)
}.toOption()

