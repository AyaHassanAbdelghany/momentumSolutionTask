package com.example.momentumsolutiontask.ui.productScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momentumsolutiontask.repository.data.DataRepo
import javax.inject.Inject

class ProductModelFactory @Inject constructor(
private val dataRepo: DataRepo
) : ViewModelProvider.Factory
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                ProductViewModel(dataRepo) as T
            } else {
                throw IllegalArgumentException("This Class Could not br found")
            }
        }
    }