package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import com.google.gson.Gson
import com.marvel.dmsmith.marvelchallenge.models.CreatorList
import org.junit.Test
import org.junit.Assert.assertEquals
import java.io.File

class ComicDetailUtilTest {
    private val gson = Gson()

    private val creators: CreatorList by lazy {
        val jsonUrl = javaClass.classLoader.getResource("creators.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, CreatorList::class.java)
    }

    private val expectedCreatorsString: String by lazy {
        "penciller (cover): Paul Gulacy \n" +
                "writer: Paul Jenkins \n"
    }

    private val emptyCreators: CreatorList by lazy {
        val jsonUrl = javaClass.classLoader.getResource("emptyCreators.json")
        val json = File(jsonUrl.file).readText()
        gson.fromJson(json, CreatorList::class.java)
    }

    @Test
    fun testFormatPublishDate() {
        val result = ComicDetailUtil.formatPublishDate("Sun Dec 30 19:00:00 EST 2029")
        assertEquals("Dec 30, 2029", result)
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
        assertEquals(expectedCreatorsString, result)
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