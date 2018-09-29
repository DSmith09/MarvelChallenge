package com.marvel.dmsmith.marvelchallenge.base

interface BaseContract {
    interface View {
        fun displayError(error: String)
    }

    interface Presenter<V> {
        fun attach(view: V)
        fun unsubscribe()
    }
}