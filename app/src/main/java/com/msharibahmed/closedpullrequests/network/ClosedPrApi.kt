package com.msharibahmed.closedpullrequests.network

import com.msharibahmed.closedpullrequests.models.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClosedPrApi {

    @GET("repos/{user}/{repo}/pulls?state=closed")
    suspend fun getAllClosedPR(
        @Path(value = "user") user: String,
        @Path(value = "repo") repo: String
    ): Response<List<Item>>

}