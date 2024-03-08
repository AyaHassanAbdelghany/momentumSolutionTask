package com.example.momentumsolutiontask.repository.source.remote

import com.example.momentumsolutiontask.network.DataService
import javax.inject.Inject

class DataRemoteSource @Inject constructor(private val dataService: DataService) {

    suspend fun  getProduct() = dataService.getProducts()
}