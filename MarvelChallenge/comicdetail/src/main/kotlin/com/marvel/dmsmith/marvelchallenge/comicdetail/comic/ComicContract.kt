package com.marvel.dmsmith.marvelchallenge.comicdetail.comic

import com.marvel.dmsmith.marvelchallenge.base.BaseContract
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails

interface ComicContract {
    interface View: BaseContract.View {
        fun displayComic(comic: ComicDetails)
        fun displayComics(comics: List<ComicDetails>)
        fun loadComics()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun fetchComic(id: Int)
        fun fetchComics()
    }
}