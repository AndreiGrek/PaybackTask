package com.example.paybacktask.domain

import retrofit2.Response

interface PaybackRepository {
    suspend fun getAllHits(query: String): Response<PixabayResponse>
}