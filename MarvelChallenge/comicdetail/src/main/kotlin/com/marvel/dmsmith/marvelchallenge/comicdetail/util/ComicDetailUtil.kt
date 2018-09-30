package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import android.text.format.DateFormat
import com.marvel.dmsmith.marvelchallenge.models.CreatorList
import java.util.*

object ComicDetailUtil {

    fun formatCreators(creators: CreatorList?): String {
        val stringBuilder = StringBuilder()
        if (creators?.items == null) return ""
        for (creator in creators.items!!) {
            stringBuilder.append("${creator.role}: ${creator.name} \n")
        }
        return stringBuilder.toString()
    }

    fun formatPublishDate(publishDate: String?): String {
        if (publishDate == null) return ""
        return DateFormat.format("MMM d, yyyy", Date(publishDate)) as String
    }
}
