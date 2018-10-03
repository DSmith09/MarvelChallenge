package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        simpleDateFormat.format(Date(this))
    } catch (e: Exception) {
        ""
    }
}