package com.amunocis.novaera.model.remote.pojo

import com.google.gson.annotations.SerializedName

data class PhoneWrapper(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val image: String
)
