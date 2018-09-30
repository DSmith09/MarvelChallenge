package com.marvel.dmsmith.marvelchallenge.comicdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marvel.dmsmith.marvelchallenge.comicdetail.fragments.ComicViewFragment

class ComicDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val uri = intent.data

        val comicId = if (uri != null && uri.queryParameterNames.contains("id")) {
            val id = uri.getQueryParameter("id")
            id.toIntOrNull() ?: 0
        } else {
            0
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ComicViewFragment.newInstance(comicId))
                .commit()
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}