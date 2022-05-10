package com.example.paybacktask.data

import com.example.paybacktask.domain.PaybackRepository
import javax.inject.Inject

class PaybackRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : PaybackRepository {
    override suspend fun getAllHits(query: String) = retrofitService.getAllHits(query)
}