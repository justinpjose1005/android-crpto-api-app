package com.onjuno.assignment.crypto.models

sealed class NetworkResponse {
    data class Success(val feedDataModel: FeedDataModel?) : NetworkResponse()
    object Loading : NetworkResponse()
    object Failure : NetworkResponse()
}
