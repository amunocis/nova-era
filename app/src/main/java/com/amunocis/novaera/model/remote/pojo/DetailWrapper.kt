package com.amunocis.novaera.model.remote.pojo

import com.google.gson.annotations.SerializedName

data class DetailWrapper(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lastPrice")
    val lastPrice: Int,
    @SerializedName("credit")
    val credit: Boolean
)
