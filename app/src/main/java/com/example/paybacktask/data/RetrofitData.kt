package com.example.paybacktask.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitData {

    companion object {
        private const val URL = "https://pixabay.com/"
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {

                val interceptor = HttpLoggingInterceptor();
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val client =  OkHttpClient.Builder()
                    .addInterceptor(interceptor)
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