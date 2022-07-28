package com.msharibahmed.closedpullrequests.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("closed_at")
    val closedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User
)