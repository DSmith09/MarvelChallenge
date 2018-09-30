package com.marvel.dmsmith.marvelchallenge.comicdetail.comic

import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ComicPresenter(override val kodein: Kodein): ComicContract.Presenter, KodeinAware {

    private val api: MarvelApi by instance()

    private var view: ComicContract.View? = null

    override fun attach(view: ComicContract.View) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun fetchComic(id: Int) {
        api.getComic(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataWrapper ->
                    val comic = dataWrapper.data?.results?.let { it.first() }
                    val comicImage = comic?.images?.let { it.first() }
                    val imageUrl = "${comicImage?.path}.${comicImage?.extension}".
                            replace("http", "https")
                    val pubDate = comic?.dates?.let { it.first().date.toString() }

                    val comicDetails = ComicDetails(title = comic?.title,
                                                    imageUrl = imageUrl,
                                                    description = comic?.description,
                                                    creators = comic?.creators,
                                                    pubDate = pubDate
                            )
                    view?.displayComic(comicDetails)
                }, { error ->
                    view?.displayError(error.localizedMessage)
                    view?.loadComics()
                })
    }

    override fun fetchComics() {
        api.getComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataWrapper ->
                    val results = ArrayList<ComicDetails>()
                    dataWrapper.data?.results?.let { comics ->
                        comics.forEach { comic ->
                            comic.images?.let { images ->
                                if (images.isNotEmpty()) {
                                    val comicImage = images.first()
                                    val imageUrl = "${comicImage.path}.${comicImage.extension}".
                                            replace("http", "https")
                                    val pubDate = comic.dates?.let { it.first().date.toString() }
                                    val comicDetails = ComicDetails(title = comic.title,
                                                                    imageUrl = imageUrl,
                                                                    description = comic.description,
                                                                    creators = comic.creators,
                                                                    pubDate = pubDate
                                                                    )
                                    results.add(comicDetails)
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