package com.marvel.dmsmith.marvelchallenge.comicdetail.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.marvel.dmsmith.marvelchallenge.models.CharacterDataWrapper
import com.marvel.dmsmith.marvelchallenge.models.ComicDataWrapper
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import io.reactivex.Observable
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MockApi: MarvelApi {

    enum class RequestType(val id: Int) {
        PASS(1),
        FAIL(-1)
    }

    private val gson: Gson by lazy {
        val builder = GsonBuilder()
                .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
                    val date = json?.asString
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSSS", Locale.getDefault())
                    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                    try {
                        simpleDateFormat.parse(date)
                    } catch (e: Exception) {
                        return@JsonDeserializer Date()
                    }
                })
        builder.create()
    }

    private val characters: CharacterDataWrapper by lazy {
        val jsonUrl = javaClass.classLoader.getResource("characters.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, CharacterDataWrapper::class.java)
    }

    private val character: CharacterDataWrapper by lazy {
        val jsonUrl = javaClass.classLoader.getResource("character.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, CharacterDataWrapper::class.java)
    }

    private val comics: ComicDataWrapper by lazy {
        val jsonUrl = javaClass.classLoader.getResource("comics.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, ComicDataWrapper::class.java)
    }

    private val comic: ComicDataWrapper by lazy {
        val jsonUrl = javaClass.classLoader.getResource("comic.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, ComicDataWrapper::class.java)
    }

    override fun getCharacters(): Observable<CharacterDataWrapper> {
        return Observable.create { it.onNext(characters) }
    }

    override fun getCharacter(id: Int): Observable<CharacterDataWrapper> {
        return Observable.create {
            when (id) {
                RequestType.PASS.id -> it.onNext(character)
                RequestType.FAIL.id -> it.onError(Error("Invalid Request"))
            }
        }
    }

    override fun getComics(): Observable<ComicDataWrapper> {
        return Observable.create { it.onNext(comics) }
    }

    override fun getComic(id: Int): Observable<ComicDataWrapper> {
        return Observable.create {
            when (id) {
                RequestType.PASS.id -> it.onNext(comic)
                RequestType.FAIL.id -> it.onError(Error("Invalid Request"))
            }
        }
    }
}