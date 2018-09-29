package com.marvel.dmsmith.marvelchallenge.base.models

data class CharacterDataWrapper(
        val code: Int?,
        val status: String?,
        val copyright: String?,
        val attributionText: String?,
        val attributionHTML: String?,
        val data: CharacterDataContainer?,
        val etag: String?
)