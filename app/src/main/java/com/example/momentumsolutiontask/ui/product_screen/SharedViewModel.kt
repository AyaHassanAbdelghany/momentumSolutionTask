package com.example.momentumsolutiontask.ui.product_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.momentumsolutiontask.pojo.ProductResponse


class SharedViewModel:ViewModel() {

    private val _product: MutableLiveData<ProductResponse> = MutableLiveData()
    var product: LiveData<ProductResponse> = _product


    fun setLiveDataValue(newValue: ProductResponse) {
        _product.value = newValue
    }
}