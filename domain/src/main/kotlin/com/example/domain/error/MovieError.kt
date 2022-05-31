package com.example.domain.error

sealed class MovieError

object ErrorOnConnection: MovieError()
object InvalidParametersError: MovieError()
object AuthenticationError: MovieError()
object NotFoundError: MovieError()
object IOOperationError: MovieError()
object UnknownIOError: MovieError()
class GenericError(val message: String = ""): MovieError()
