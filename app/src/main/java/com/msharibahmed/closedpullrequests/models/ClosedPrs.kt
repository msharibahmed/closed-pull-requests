package com.msharibahmed.closedpullrequests.models


import com.google.gson.annotations.SerializedName

data class ClosedPrs(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)