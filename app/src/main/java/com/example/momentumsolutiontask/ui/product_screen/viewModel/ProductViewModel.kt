package com.example.momentumsolutiontask.ui.product_screen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.repository.data.DataRepo
import com.example.momentumsolutiontask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val dataRepo: DataRepo,

    ) : ViewModel() {

    private var _products: MutableLiveData<ResponseState<List<ProductResponse>>> = MutableLiveData()
    var products: LiveData<ResponseState<List<ProductResponse>>> = _products

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = dataRepo.getProduct()
            withContext(Dispatchers.Main) {
                response.collect {
                    _products.value = it
                    deleteProductsFromRoom()
                    if(it is ResponseState.Success)
                        insertProducts(it.data)
                }
            }

        }
    }

    fun getProductsFromRoom() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = dataRepo.getProductsFromRoom()
            withContext(Dispatchers.Main) {
                response.collect {
                    _products.value = it
                }
            }
        }
    }

      private fun deleteProductsFromRoom() {
          viewModelScope.launch(Dispatchers.IO) {
            dataRepo.deleteProductsFromRoom()
          }
    }

      private fun insertProducts(products: List<ProductResponse>) {
          viewModelScope.launch(Dispatchers.IO) {
           dataRepo.insertProducts(products)
          }
      }
}