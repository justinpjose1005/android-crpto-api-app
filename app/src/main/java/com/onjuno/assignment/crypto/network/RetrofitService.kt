package com.onjuno.assignment.crypto.network

import com.onjuno.assignment.crypto.models.FeedDataModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/empty-home")
    suspend fun getEmptyStateFeed(): Response<FeedDataModel>

    @GET("/home")
    suspend fun getValueStateFeed(): Response<FeedDataModel>
}