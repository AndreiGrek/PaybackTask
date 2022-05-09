package com.example.paybacktask.data

import com.example.paybacktask.domain.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/api/?key=27280788-eaad89dd8cf4e500ae82c0243")
    suspend fun getAllHits(@Query("q") query: String): Response<PixabayResponse>
}