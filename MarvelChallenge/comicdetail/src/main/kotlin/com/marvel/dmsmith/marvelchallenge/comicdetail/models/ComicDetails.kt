package com.marvel.dmsmith.marvelchallenge.comicdetail.models

import com.marvel.dmsmith.marvelchallenge.models.CreatorList
import java.io.Serializable

data class ComicDetails(
        val title: String?,
        val creators: CreatorList?,
        val description: String?,
        val imageUrl: String?,
        val pubDate: String?
): Serializable