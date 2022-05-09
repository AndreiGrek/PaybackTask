package com.example.paybacktask.domain

import retrofit2.Response

interface Repository {
    suspend fun getAllHits(): Response<PixabayResponse>
}