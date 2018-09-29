package com.marvel.dmsmith.marvelchallenge.models

data class SeriesList(
        val available: Int?,
        val returned: Int?,
        val collectionURI: String?,
        val items: Array<SeriesSummary>?
)