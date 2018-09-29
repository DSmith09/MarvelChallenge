package com.marvel.dmsmith.marvelchallenge.base

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(), KodeinAware {

    // Extension for Retrieving App Singleton
    val Context.app: App
        get() = applicationContext as App

    // Kodein Container
    override val kodein: Kodein = Kodein {
        bind<MarvelApi>() with eagerSingleton {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://gateway.marvel.com")
                    .build()
            retrofit.create(MarvelApi::class.java)
        }
    }
}