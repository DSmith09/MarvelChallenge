package com.marvel.dmsmith.marvelchallenge

import android.app.Application
import android.content.Context
import com.google.gson.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

// Extension for Retrieving App Singleton
val Context.app: App
    get() = App.instance

class App: Application(), KodeinAware {

    companion object {
        lateinit var instance: App
            private set
        private lateinit var apiKeyName: String
        private lateinit var apiKeyValue: String
        private lateinit var refererKey: String
        private lateinit var refererValue: String
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiKeyValue = getString(R.string.marvel_api_key)
        apiKeyName = getString(R.string.marvel_api_key_name)
        refererKey = getString(R.string.marvel_header_referer_key)
        refererValue = getString(R.string.marvel_header_referer_value)
    }

    // Kodein Container
    override val kodein: Kodein = Kodein {

        bind<MarvelApi>() with eagerSingleton {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(instance()))
                    .baseUrl("https://gateway.marvel.com:443")
                    .client(instance())
                    .build()
            retrofit.create(MarvelApi::class.java)
        }

        bind<OkHttpClient>() with singleton {
            val builder = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val originalRequest = chain.request()
                        val originalUrl = originalRequest.url()
                        val url = originalUrl.newBuilder()
                                .addQueryParameter(apiKeyName, apiKeyValue)
                                .build()
                        val request = originalRequest.newBuilder()
                                .header(refererKey, refererValue)
                                .url(url)
                                .build()
                        chain.proceed(request)
                    }
            builder.build()
        }

        bind<Gson>() with singleton {
            val builder = GsonBuilder()
                    .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
                        val date = json?.asString
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSSS")
                        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                        try {
                            simpleDateFormat.parse(date)
                        } catch (e: Exception) {
                            return@JsonDeserializer Date()
                        }
                    })
            builder.create()
        }
    }

}