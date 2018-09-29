package com.marvel.dmsmith.marvelchallenge.comicdetail.comic

import com.marvel.dmsmith.marvelchallenge.base.BaseContract

interface ComicContract {
    interface View: BaseContract.View {
        fun displayComicArtwork(uri: String)
        fun displayComics(uriList: List<String>)
        fun loadComics()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun fetchComicArtwork(id: Int)
        fun fetchComicsArtwork()
    }
}