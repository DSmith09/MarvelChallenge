package com.marvel.dmsmith.marvelchallenge.comicdetail.adapter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvel.dmsmith.marvelchallenge.comicdetail.R
import com.marvel.dmsmith.marvelchallenge.comicdetail.listeners.ComicDetailsTransistionListener
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_view.view.*

class ComicAdapter(private val context: Context?): RecyclerView.Adapter<ComicViewHolder>() {
    private lateinit var comics: List<ComicDetails>

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ComicViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comic_view, viewGroup, false)
        return ComicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comics.count()
    }

    override fun onBindViewHolder(viewHolder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        viewHolder.onBind(comic)
    }

    fun notifyDataSetChanged(comics: List<ComicDetails>) {
        this.comics = comics
        notifyDataSetChanged()
    }
}

class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(comic: ComicDetails) {
        Picasso.with(itemView.context)
                .load(comic.imageUrl)
                .into(itemView.comic_image_view)
        itemView.comic_image_view.visibility = View.VISIBLE
        itemView.comic_progress_bar.visibility = View.GONE
        itemView.comic_image_view.setOnClickListener(ComicDetailsTransistionListener(itemView.context as? AppCompatActivity, comic))
    }
}