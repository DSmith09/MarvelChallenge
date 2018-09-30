package com.marvel.dmsmith.marvelchallenge.network

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.marvel.dmsmith.marvelchallenge.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.eagerSingleton
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

const val CACHE_SIZE: Long = 5 * 1024 * 1024 // 5 MB Cache Size

val cache: (Context) -> (Cache) = {
    Cache(it.cacheDir, CACHE_SIZE)
}

val hasNetwork: (Context) -> (Boolean) = {
    val manager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val activeNetwork = manager?.activeNetworkInfo
    activeNetwork?.isConnected ?: false
}

val NetworkModule: (Context) -> (Kodein.Module) = { context ->
    Kodein.Module("NetworkModule") {

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
                    .cache(cache(context))
                    .addInterceptor { chain ->
                        val originalRequest = chain.request()
                        val originalUrl = originalRequest.url()
                        val url = originalUrl.newBuilder()
                                .addQueryParameter(App.apiKeyName, App.apiKeyValue)
                                .build()
                        val requestBuilder = originalRequest.newBuilder()
                                .header(App.refererKey, App.refererValue)
                                .url(url)
                        // Grab Latest if older than 15 seconds
                        val cacheValue = if (hasNetwork(context)){
                            "public, max-age=${15}"
                        }
                        // If Network Down, Use Data From Last 24hrs
                        else {
                            "public, only-if-cached, max-stale=${ 60 * 60 * 24 }"
                        }
                        val request = requestBuilder.header("Cache-Control", cacheValue).build()
                        chain.proceed(request)
                    }
            builder.build()
        }

        bind<Gson>() with singleton {
            val builder = GsonBuilder()
                    .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
                        val date = json?.asString
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSSS", Locale.US)
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