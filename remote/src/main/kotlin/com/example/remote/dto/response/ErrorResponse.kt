package com.example.remote.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ErrorResponse(
    @Json(name = "status_message")
    val statusMessage: String,
    @Json(name = "status_code")
    val statusCode: Int
)