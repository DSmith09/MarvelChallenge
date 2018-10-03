package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import com.marvel.dmsmith.marvelchallenge.models.CreatorList

fun CreatorList.formatList(): String {
    val stringBuilder = StringBuilder()
    if (this.items == null) return ""
    for (creator in this.items!!) {
        stringBuilder.append("${creator.role}: ${creator.name} \n")
    }
    return stringBuilder.toString()
}