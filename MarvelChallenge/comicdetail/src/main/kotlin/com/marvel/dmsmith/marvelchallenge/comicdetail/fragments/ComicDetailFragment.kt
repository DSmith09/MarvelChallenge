package com.marvel.dmsmith.marvelchallenge.comicdetail.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.marvel.dmsmith.marvelchallenge.comicdetail.ComicDetailActivity
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import com.marvel.dmsmith.marvelchallenge.comicdetail.util.ComicDetailUtil
import com.squareup.picasso.Picasso
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
        setOnClickListeners()
    }

    private fun populateView() {
        val comic = arguments?.getSerializable(COMIC_KEY) as? ComicDetails

        (activity as? ComicDetailActivity)?.setActionBarTitle(comic?.title)

        Picasso.with(context)
                .load(comic?.imageUrl)
                .fit()
                .into(comic_image_view)

        comic_description.text = getString(R.string.description_label, comic?.description ?: "")
        comic_pub_date.text = getString(R.string.publish_date_label, ComicDetailUtil.formatPublishDate(comic?.pubDate))
        comic_authors.text = getString(R.string.creators_label, "\n${ComicDetailUtil.formatCreators(comic?.creators)}")
    }

    private fun setOnClickListeners() {
        read_now_button.setOnClickListener {
            Toast.makeText(context, "Read Now Tapped", Toast.LENGTH_SHORT).show()
        }

        sign_in_button.setOnClickListener {
            Toast.makeText(context, "Sign In Tapped", Toast.LENGTH_SHORT).show()
        }
    }
}