package com.marvel.dmsmith.marvelchallenge.models

data class ComicList(
        val available: Int?,
        val returned: Int?,
        val collectionURI: String?,
        val items: Array<ComicSummary>?
)