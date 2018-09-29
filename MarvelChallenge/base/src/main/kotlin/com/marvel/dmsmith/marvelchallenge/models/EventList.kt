package com.marvel.dmsmith.marvelchallenge.models

data class EventList(
        val available: Int?,
        val returned: Int?,
        val collectionURI: String?,
        val items: Array<EventSummary>?
)