package com.marvel.dmsmith.marvelchallenge.comicdetail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.marvel.dmsmith.marvelchallenge.comicdetail.ComicDetailActivity
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.marvel.dmsmith.marvelchallenge.comicdetail.adapter.ComicAdapter
import com.marvel.dmsmith.marvelchallenge.comicdetail.comic.ComicContract
import com.marvel.dmsmith.marvelchallenge.comicdetail.comic.ComicPresenter
import com.marvel.dmsmith.marvelchallenge.comicdetail.listeners.ComicDetailsTransistionListener
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_view.*

class ComicViewFragment: Fragment(), ComicContract.View {

    private val presenter: ComicPresenter by lazy { ComicPresenter(context!!) }

    companion object {
        private const val COMIC_KEY = "comic_key"
        val newInstance: (Int) -> ComicViewFragment = { comicId ->
            ComicViewFragment().apply {
                val bundle = Bundle()
                bundle.putInt(COMIC_KEY, comicId)
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comic_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? ComicDetailActivity)?.setActionBarTitle(getString(com.marvel.dmsmith.marvelchallenge.R.string.app_name))
        fetchComic()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    private fun fetchComic() {
        val comicId = arguments?.getInt(COMIC_KEY)
        if (comicId != null && comicId != 0) {
            presenter.fetchComic(comicId)
        } else {
            loadComics()
        }
    }

    override fun displayError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun loadComics() {
        comic_recycler_view.layoutManager = LinearLayoutManager(context)
        comic_recycler_view.adapter = ComicAdapter(context)
        presenter.fetchComics()
    }

    override fun displayComic(comic: ComicDetails) {
        Picasso.with(context)
                .load(comic.imageUrl)
                .into(comic_image_view)
        comic_image_view.visibility = View.VISIBLE
        comic_progress_bar.visibility = View.GONE
        comic_image_view.setOnClickListener(ComicDetailsTransistionListener(activity, comic))
    }

    override fun displayComics(comics: List<ComicDetails>) {
        (comic_recycler_view.adapter as? ComicAdapter)?.notifyDataSetChanged(comics)
        comic_recycler_view.visibility = View.VISIBLE
        comic_progress_bar.visibility = View.GONE
    }
}