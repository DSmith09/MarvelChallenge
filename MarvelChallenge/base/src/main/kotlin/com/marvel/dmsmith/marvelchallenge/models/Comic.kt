package com.marvel.dmsmith.marvelchallenge.models

import java.util.*

data class Comic(
        val id: Int?,
        val digitalId: Int?,
        val title: String?,
        val issueNumber: Double?,
        val variantDescription: String?,
        val description: String?,
        val modified: Date?,
        val isbn: String?,
        val upc: String?,
        val diamondCode: String?,
        val ean: String?,
        val issn: String?,
        val format: String?,
        val pageCount: Int?,
        val textObjects: Array<TextObject>?,
        val resourceURI: String?,
        val urls: Array<Url>?,
        val series: SeriesSummary?,
        val variants: Array<ComicSummary>?,
        val collections: Array<ComicSummary>?,
        val collectedIssues: Array<ComicSummary>?,
        val dates: Array<ComicDate>?,
        val prices: Array<ComicPrice>?,
        val thumbnail: Image?,
        val images: Array<Image>?,
        val creators: CreatorList?,
        val characters: CharacterList?,
        val stories: StoryList?,
        val events: EventList?
)