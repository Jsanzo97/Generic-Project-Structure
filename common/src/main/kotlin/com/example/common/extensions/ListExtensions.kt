package com.example.common.extensions

fun<T> List<T>.toFormattedString(): String {
    return toString()
        .replace("[", "")
        .replace("]", "")
}