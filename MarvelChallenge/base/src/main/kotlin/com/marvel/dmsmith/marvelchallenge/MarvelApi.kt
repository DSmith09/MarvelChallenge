package com.marvel.dmsmith.marvelchallenge

import com.marvel.dmsmith.marvelchallenge.models.CharacterDataWrapper
import com.marvel.dmsmith.marvelchallenge.models.ComicDataWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    /** CHARACTERS **/
    @GET("/v1/public/characters")
    fun getCharacters(): Observable<CharacterDataWrapper>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") id: Int): Observable<CharacterDataWrapper>

    /** COMICS **/
    @GET("/v1/public/comics")
    fun getComics(): Observable<ComicDataWrapper>

    @GET("/v1/public/comics/{comicId}")
    fun getComic(@Path("comicId") id: Int): Observable<ComicDataWrapper>
}