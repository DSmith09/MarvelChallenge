package com.marvel.dmsmith.marvelchallenge.comicdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import com.marvel.dmsmith.marvelchallenge.app
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.generic.instance

class ComicDetailActivity : AppCompatActivity() {
    private val api: MarvelApi by app.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        fetchComics()
    }

    private fun fetchComics() {
        api.getComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ wrapper ->
                    print("We Got Some Data Baby!")
                    val something = wrapper.data
                    val somethingElse = something?.count
                }, {
                    print(it.localizedMessage)
                })
    }
}
