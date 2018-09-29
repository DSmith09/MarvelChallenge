package com.marvel.dmsmith.marvelchallenge.comicdetail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvel.dmsmith.marvelchallenge.comicdetail.ComicDetailActivity
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import kotlinx.android.synthetic.main.comic_detail_view.*

class ComicDetailFragment: Fragment() {

    companion object {
        private const val COMIC_KEY = "comic_key"
        val newInstance: (ComicDetails) -> ComicDetailFragment = { comic ->
            ComicDetailFragment().apply {
                val bundle = Bundle()
                bundle.putSerializable(COMIC_KEY, comic)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comic_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
    }

    private fun populateView() {
        val comic = arguments?.getSerializable(COMIC_KEY) as? ComicDetails
        (activity as? ComicDetailActivity)?.setActionBarTitle(comic?.title) // Title

        Log.e("COMIC DETAILS VIEW", "Comic Description is: ${comic?.description}")
        comic_progress_bar.visibility = View.GONE
        comic_description.visibility = View.VISIBLE
    }
}