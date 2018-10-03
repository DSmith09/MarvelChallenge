package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import com.google.gson.Gson
import com.marvel.dmsmith.marvelchallenge.models.CreatorList
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class CreatorListExtensionsTest {

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
    fun testFormat() {
        val result = creators.formatList()
        assertEquals(expectedCreatorsString, result)
    }

    @Test
    fun testFormat_EmptyItems() {
        val result = emptyCreators.formatList()
        assertEquals("", result)
    }
}