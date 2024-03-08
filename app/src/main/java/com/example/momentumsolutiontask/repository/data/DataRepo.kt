package com.example.momentumsolutiontask.repository.data

import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.repository.source.local.ProductDao
import com.example.momentumsolutiontask.repository.source.remote.DataRemoteSource
import com.example.momentumsolutiontask.utils.ResponseState
import com.example.momentumsolutiontask.utils.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepo @Inject constructor(
    private val dataRemoteSource: DataRemoteSource,
    private val productDao: ProductDao
) : IDataRepo {

    override suspend fun getProduct(): Flow<ResponseState<List<ProductResponse>>> {
        return flow {
            emit(ResponseState.Loading)
            try {
                val response = dataRemoteSource.getProduct()
                emit(Util.handleResponse(response))
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertProducts(products: List<ProductResponse>){
            productDao.insertProducts(products)
    }

    override suspend fun getProductsFromRoom(): Flow<ResponseState<List<ProductResponse>>> {
        return flow {
            emit(ResponseState.Loading)
            try {
                val response = productDao.getProducts()
                emit(ResponseState.Success(response))

            } catch (e: Exception) {
                emit(ResponseState.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteProductsFromRoom(){
        productDao.deleteProducts()
    }
}
