package com.msharibahmed.closedpullrequests.repository

import android.util.Log
import com.msharibahmed.closedpullrequests.models.Item
import com.msharibahmed.closedpullrequests.network.ClosedPrApi
import com.msharibahmed.closedpullrequests.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClosedPrRepository constructor(
    private val closedPrApi: ClosedPrApi,
) {
    suspend fun getClosedPrs(
        user: String,
        repo: String
    ): Flow<DataState<List<Item>>> = flow {
        emit(DataState.Loading)
        try {
            val closedPrResponse =
                closedPrApi.getAllClosedPR(user = user, repo = repo).body()
            Log.d("MY_TAG",closedPrResponse.toString())
            emit(DataState.Success(closedPrResponse!!))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}