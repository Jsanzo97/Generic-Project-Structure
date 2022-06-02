package com.example.data.error

import com.example.domain.error.*

sealed class RemoteDataError

object InvalidRequest: RemoteDataError()
object InvalidCredentials: RemoteDataError()
object NotFound: RemoteDataError()
data class UnrecognizedRemoteError(val message: String = "") : RemoteDataError()

fun RemoteDataError.toMovieError() = when (this) {
    InvalidRequest -> InvalidParametersError
    InvalidCredentials -> AuthenticationError
    NotFound -> NotFoundError
    is UnrecognizedRemoteError -> GenericError(message)
}