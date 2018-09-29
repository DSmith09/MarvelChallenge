package com.marvel.dmsmith.marvelchallenge.comicdetail.comic

import com.marvel.dmsmith.marvelchallenge.App
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.generic.instance

class ComicPresenter: ComicContract.Presenter {

    var view: ComicContract.View? = null
    private val api: MarvelApi by App.instance.kodein.instance()

    override fun attach(view: ComicContract.View) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun fetchComicArtwork(id: Int) {
        api.getComic(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataWrapper ->
                    val comicImage = dataWrapper.data?.results?.let { comics ->
                        comics.first().images?.first()
                    }
                    val imageUrl = "${comicImage?.path}.${comicImage?.extension}".
                        replace("http", "https")
                    view?.displayComicArtwork(imageUrl)
                }, { error ->
                    view?.displayError(error.localizedMessage)
                    view?.loadComics()
                })
    }

    override fun fetchComicsArtwork() {
        api.getComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataWrapper ->
                    val results = ArrayList<String>()
                    dataWrapper.data?.results?.let { comics ->
                        comics.forEach { comic ->
                            comic.images?.let { images ->
                                if (images.isNotEmpty()) {
                                    val comicImage = images.first()
                                    val imageUrl = "${comicImage.path}.${comicImage.extension}".
                                            replace("http", "https")
                                    results.add(imageUrl)
                                }
                            }
                        }
                    }
                    view?.displayComics(results)
                }, { error ->
                    view?.displayError(error.localizedMessage)
                })
    }
}