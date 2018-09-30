package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import com.marvel.dmsmith.marvelchallenge.models.CreatorList
import org.junit.Test
import org.junit.Assert.assertEquals

class ComicDetailUtilTest {

    lateinit var creators: CreatorList

    lateinit var emptyCreators: CreatorList

    @Test
    fun testFormatPublishDate() {
        val result = ComicDetailUtil.formatPublishDate("")
    }

    @Test
    fun testFormatPublishDate_Null() {
        val result = ComicDetailUtil.formatPublishDate(null)
        assertEquals("", result)
    }

    @Test
    fun testFormatPublishDate_InvalidDate() {
        val result = ComicDetailUtil.formatPublishDate("foo")
        assertEquals("", result)
    }

    @Test
    fun testFormatCreators() {
        val result = ComicDetailUtil.formatCreators(creators)

    }

    @Test
    fun testFormatCreators_Null() {
        val result = ComicDetailUtil.formatCreators(null)
        assertEquals("", result)
    }

    @Test
    fun testFormatCreators_EmptyCreatorList() {
        val result = ComicDetailUtil.formatCreators(emptyCreators)
        assertEquals("", result)
    }
}