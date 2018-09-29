package com.marvel.dmsmith.marvelchallenge.base.models

data class CharacterList(
        val available: Int?,
        val returned: Int?,
        val collectionURI: String?,
        val items: Array<CharacterSummary>?
)