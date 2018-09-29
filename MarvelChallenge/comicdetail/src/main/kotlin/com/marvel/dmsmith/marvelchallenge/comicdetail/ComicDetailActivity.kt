package com.marvel.dmsmith.marvelchallenge.comicdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ComicDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data
        val appLinkDataString = appLinkIntent.dataString
        setContentView(R.layout.activity_comic_detail)
    }
}
