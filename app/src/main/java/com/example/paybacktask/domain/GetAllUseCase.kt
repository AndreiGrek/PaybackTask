package com.example.paybacktask.domain

import retrofit2.Response

class GetAllUseCase(private val repository: Repository) {
    suspend fun execute(): Response<PixabayResponse> {
        return repository.getAll()
    }
}