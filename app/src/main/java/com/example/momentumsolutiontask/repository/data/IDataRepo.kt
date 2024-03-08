package com.example.momentumsolutiontask.repository.data

import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface IDataRepo {
    suspend fun getProduct(): Flow<ResponseState<List<ProductResponse>>>
    suspend fun insertProducts(products:List<ProductResponse>)
    suspend fun getProductsFromRoom() :Flow<ResponseState<List<ProductResponse>>>
    suspend fun deleteProductsFromRoom()

}