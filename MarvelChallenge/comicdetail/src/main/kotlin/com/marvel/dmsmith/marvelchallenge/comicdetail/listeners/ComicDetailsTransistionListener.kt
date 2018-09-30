package com.marvel.dmsmith.marvelchallenge.comicdetail.listeners

import android.support.v4.app.FragmentActivity
import android.view.View
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.marvel.dmsmith.marvelchallenge.comicdetail.fragments.ComicDetailFragment
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails

class ComicDetailsTransistionListener(private val activity: FragmentActivity?, private val comic: ComicDetails): View.OnClickListener {

    override fun onClick(view: View?) {
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ComicDetailFragment.newInstance(comic))
                .addToBackStack(null)
                .commit()
        }
    }
}