package com.example.paybacktask.data

import com.example.paybacktask.domain.Repository

class RepositoryImpl(
    private val retrofitService: RetrofitService,
) : Repository {

    override suspend fun getAll() = retrofitService.getAll()

}