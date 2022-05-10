package com.example.paybacktask.domain

import retrofit2.Response
import javax.inject.Inject

class GetAllHitsUseCase @Inject constructor(private val paybackRepository: PaybackRepository) {
    suspend fun execute(query: String): Response<PixabayResponse> {
        return paybackRepository.getAllHits(query)
    }
}