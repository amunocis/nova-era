package com.amunocis.novaera.model.remote

import com.amunocis.novaera.model.remote.pojo.DetailWrapper
import com.amunocis.novaera.model.remote.pojo.PhoneWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("products")
    suspend fun fetchPhoneList(): Response<List<PhoneWrapper>>

    @GET("details/{id}")
    suspend fun fetchPhoneDetail(@Path("id")id: Int): Response<DetailWrapper>
}