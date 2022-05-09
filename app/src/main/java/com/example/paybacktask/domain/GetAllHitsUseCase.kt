package com.example.paybacktask.domain

import retrofit2.Response

class GetAllHitsUseCase(private val repository: Repository) {
    suspend fun execute(): Response<PixabayResponse> {
        return repository.getAllHits()
    }
}