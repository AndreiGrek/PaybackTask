package com.example.paybacktask.domain

import retrofit2.Response

class GetAllHitsUseCase(private val repository: Repository) {
    suspend fun execute(query: String): Response<PixabayResponse> {
        return repository.getAllHits(query)
    }
}