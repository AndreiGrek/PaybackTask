package com.example.paybacktask.data

import com.example.paybacktask.PixabyApplication
import com.example.paybacktask.utils.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitData {

    companion object {

        private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
        private val myCache = Cache(PixabyApplication.applicationContext().cacheDir, CACHE_SIZE)
        private const val URL = "https://pixabay.com/"

        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {

                val client =  OkHttpClient.Builder()
                    .cache(myCache)
                    .addInterceptor { chain ->

                        var request = chain.request()

                        request = if (Utils.hasNetwork(PixabyApplication.applicationContext()))
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                        else
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                        chain.proceed(request)
                    }
                    .build();

                val retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}