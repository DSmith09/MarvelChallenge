package com.marvel.dmsmith.marvelchallenge.comicdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.marvel.dmsmith.marvelchallenge.comicdetail.fragments.ComicViewFragment

class ComicDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)

        val uri = intent.data
        val comicId = uri.getQueryParameter("id").toInt()

        Log.e("COMIC DETAIL ACTIVITY", "COMIC ID IS: $comicId")
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ComicViewFragment.newInstance(comicId))
                .commit()
    }
}