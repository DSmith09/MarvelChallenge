package com.marvel.dmsmith.marvelchallenge.comicdetail.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_view.view.*

class ComicAdapter(private val context: Context?): RecyclerView.Adapter<ComicViewHolder>() {
    private lateinit var comicArtworkList: List<String>

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ComicViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comic_view, viewGroup, false)
        return ComicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comicArtworkList.count()
    }

    override fun onBindViewHolder(viewHolder: ComicViewHolder, position: Int) {
        val imageUrl = comicArtworkList[position]
        viewHolder.onBind(imageUrl, context)
    }

    fun notifyDataSetChanged(artworkList: List<String>) {
        comicArtworkList = artworkList
        notifyDataSetChanged()
    }
}

class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(uri: String, context: Context?) {
        Picasso.with(context)
                .load(uri)
                .into(itemView.comic_image_view)
        itemView.comic_image_view.visibility = View.VISIBLE
        itemView.comic_progress_bar.visibility = View.GONE
    }
}