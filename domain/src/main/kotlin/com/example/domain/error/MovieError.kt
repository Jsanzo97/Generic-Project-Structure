package com.example.domain.error

sealed class MovieError

object ErrorOnConnection: MovieError()
object InvalidParametersError: MovieError()
object AuthenticationError: MovieError()
object NotFoundError: MovieError()
class GenericError(val message: String = ""): MovieError()
