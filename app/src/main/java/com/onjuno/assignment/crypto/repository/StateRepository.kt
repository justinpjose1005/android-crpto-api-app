package com.onjuno.assignment.crypto.repository

import com.onjuno.assignment.crypto.models.NetworkResponse
import com.onjuno.assignment.crypto.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StateRepository(private val retrofitService: RetrofitService) {
    suspend fun getEmptyStateFeed(): Flow<NetworkResponse> {
        return flow {
            emit(NetworkResponse.Loading)
            val response = retrofitService.getEmptyStateFeed()
            if (response.isSuccessful) {
                emit(NetworkResponse.Success(response.body()))
                return@flow
            }
            emit(NetworkResponse.Failure)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getValueStateFeed(): Flow<NetworkResponse> {
        return flow {
            emit(NetworkResponse.Loading)
            val response = retrofitService.getValueStateFeed()
            if (response.isSuccessful) {
                emit(NetworkResponse.Success(response.body()))
                return@flow
            }
            emit(NetworkResponse.Failure)
        }.flowOn(Dispatchers.IO)
    }
}