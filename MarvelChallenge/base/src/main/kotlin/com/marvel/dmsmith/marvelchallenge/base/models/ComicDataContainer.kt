package com.marvel.dmsmith.marvelchallenge.base.models

data class ComicDataContainer(
        val offset: Int?,
        val limit: Int?,
        val total: Int?,
        val count: Int?,
        val results: Array<Comic>?
)