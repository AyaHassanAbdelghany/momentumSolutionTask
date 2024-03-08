package com.example.momentumsolutiontask.network

import com.example.momentumsolutiontask.utils.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface DataService {

    @GET("$END_POINT")
    suspend fun getProducts(
    ): Response<List<ProductResponse>>
}