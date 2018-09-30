package com.marvel.dmsmith.marvelchallenge

import android.app.Application
import android.content.Context
import com.marvel.dmsmith.marvelchallenge.network.NetworkModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

// Extension For Application Class
val Context.app: App
    get() = this.applicationContext as App

class App: Application(), KodeinAware {

    companion object {
        lateinit var apiKeyName: String
            private set
        lateinit var apiKeyValue: String
            private set
        lateinit var refererKey: String
            private set
        lateinit var refererValue: String
            private set
    }

    override fun onCreate() {
        super.onCreate()
        apiKeyValue = getString(R.string.marvel_api_key)
        apiKeyName = getString(R.string.marvel_api_key_name)
        refererKey = getString(R.string.marvel_header_referer_key)
        refererValue = getString(R.string.marvel_header_referer_value)
    }

    // Kodein Container
    override val kodein: Kodein = Kodein.lazy {
        import(NetworkModule(applicationContext))
    }
}