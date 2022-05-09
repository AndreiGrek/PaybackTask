package com.example.paybacktask.data

import com.example.paybacktask.domain.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

//    @Headers("key=27280788-eaad89dd8cf4e500ae82c0243")
//    @GET("standings/?&season=2021")
    @GET ("/api/?key=27280788-eaad89dd8cf4e500ae82c0243")
    suspend fun getAllHits(): Response<PixabayResponse>

}