package com.marvel.dmsmith.marvelchallenge.comicdetail.comic

import com.marvel.dmsmith.marvelchallenge.comicdetail.api.MockApi
import com.marvel.dmsmith.marvelchallenge.comicdetail.models.ComicDetails
import com.marvel.dmsmith.marvelchallenge.network.MarvelApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ComicPresenterTest: KodeinAware {

    override val kodein: Kodein = Kodein {
        bind<MarvelApi>() with provider { MockApi() }
    }

    private lateinit var mockView: MockView
    private lateinit var presenter: ComicPresenter

    @Before
    fun setup() {
        mockView = MockView()
        presenter = ComicPresenter(kodein)
        presenter.attach(mockView)
        assertNotNull(presenter)
    }

    @After
    fun teardown() {
        presenter.unsubscribe()
    }

    @Test
    fun testFetchComic() {
        presenter.fetchComic(id = 100)
        assertTrue(mockView.comicDisplayed)
        assertFalse(mockView.comicsDisplayed)
        assertFalse(mockView.shouldLoadComics)
        assertFalse(mockView.errorDisplayed)
    }

    @Test
    fun testFetchComic_InvalidId() {
        presenter.fetchComic(id = 0)
        assertFalse(mockView.comicDisplayed)
        assertFalse(mockView.comicsDisplayed)
        assertTrue(mockView.shouldLoadComics)
        assertTrue(mockView.errorDisplayed)
    }

    @Test
    fun testFetchComics() {
        presenter.fetchComics()
        assertFalse(mockView.comicDisplayed)
        assertTrue(mockView.comicsDisplayed)
        assertFalse(mockView.shouldLoadComics)
        assertFalse(mockView.errorDisplayed)
    }

private class MockView: ComicContract.View {
        var comicDisplayed = false
        var comicsDisplayed = false
        var shouldLoadComics = false
        var errorDisplayed = false

        override fun displayComic(comic: ComicDetails) {
            comicDisplayed = true
        }

        override fun displayComics(comics: List<ComicDetails>) {
            comicsDisplayed = true
        }

        override fun loadComics() {
            shouldLoadComics = true
        }

        override fun displayError(error: String) {
            errorDisplayed = true
        }

    }
}