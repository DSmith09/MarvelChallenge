package com.marvel.dmsmith.marvelchallenge.comicdetail.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {
    private val validPublishDate = "Sun Dec 30 19:00:00 EST 2029"
    private val invalidPublishDate = "foo"
    private val expectedFormattedDate = "Dec 30, 2029"

    @Test
    fun testFormatDate() {
        val result = validPublishDate.formatDate()
        assertEquals(expectedFormattedDate, result)
    }

    @Test
    fun testFormatDate_InvalidDate() {
        val result = invalidPublishDate.formatDate()
        assertEquals("", result)
    }
}