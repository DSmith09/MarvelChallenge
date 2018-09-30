package com.marvel.dmsmith.marvelchallenge.comicdetail.api

import com.marvel.dmsmith.marvelchallenge.models.CharacterDataWrapper
import com.marvel.dmsmith.marvelchallenge.models.ComicDataWrapper
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import io.reactivex.Observable

class MockApi: MarvelApi {

    override fun getCharacters(): Observable<CharacterDataWrapper> {
        return Observable.create {
            val wrapper = CharacterDataWrapper(
                    code = 200,
                    status = "test",
                    copyright = "test",
                    attributionText = "test",
                    attributionHTML = "test",
                    data = null,
                    etag = "test"
            )
            Observable.just(wrapper)
        }
    }

    override fun getCharacter(id: Int): Observable<CharacterDataWrapper> {
        return Observable.create {
            val wrapper = CharacterDataWrapper(
                    code = 200,
                    status = "test",
                    copyright = "test",
                    attributionText = "test",
                    attributionHTML = "test",
                    data = null,
                    etag = "test"
            )
            Observable.just(wrapper)
        }

    }

    override fun getComics(): Observable<ComicDataWrapper> {
        return Observable.create {
            val wrapper = ComicDataWrapper(
                    code = 200,
                    status = "test",
                    copyright = "test",
                    attributionText = "test",
                    attributionHTML = "test",
                    data = null,
                    etag = "test"
            )
            Observable.just(wrapper)
        }
    }

    override fun getComic(id: Int): Observable<ComicDataWrapper> {
        return Observable.create {
            val wrapper = ComicDataWrapper(
                    code = 200,
                    status = "test",
                    copyright = "test",
                    attributionText = "test",
                    attributionHTML = "test",
                    data = null,
                    etag = "test"
            )
            Observable.just(wrapper)
        }
    }
}