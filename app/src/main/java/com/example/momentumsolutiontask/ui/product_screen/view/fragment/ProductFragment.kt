package com.example.momentumsolutiontask.ui.product_screen.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.momentumsolutiontask.R
import com.example.momentumsolutiontask.databinding.FragmentProductBinding
import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.ui.product_screen.SharedViewModel
import com.example.momentumsolutiontask.adapter.OnClickListener
import com.example.momentumsolutiontask.adapter.product.ProductAdapter
import com.example.momentumsolutiontask.ui.product_screen.viewModel.ProductViewModel
import com.example.momentumsolutiontask.utils.ResponseState
import com.example.momentumsolutiontask.utils.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() , OnClickListener {

    private lateinit var binding: FragmentProductBinding
    private lateinit var productAdapter: ProductAdapter
    private val productViewModel :ProductViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.toolBar.titleTxt.text = requireContext().getString(R.string.product_screen)
        setupProductAdapter()

        if (Util.isInternetAvailable(requireContext()))
            productViewModel.getProducts()
        else
            productViewModel.getProductsFromRoom()

        getProducts()
    }


    private fun getProducts() {
        productViewModel.products.observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
            when (response) {
                is ResponseState.Error -> {
                    Util.alertDialogProgress?.dismiss()
                    Util.showDialogMessage(response.message, requireContext())
                    productViewModel.products.removeObservers(viewLifecycleOwner)
                }

                is ResponseState.Success -> {
                    Util.alertDialogProgress?.dismiss()
                    productAdapter.differ.submitList(response.data)
                    productViewModel.products.removeObservers(viewLifecycleOwner)
                }

                ResponseState.Loading -> {
                    Util.showProgressDialog(requireContext())
                }
            }
        })
    }

    private fun setupProductAdapter() {
        productAdapter = ProductAdapter(this)
        binding.productRecycler.apply {
            adapter = productAdapter
        }
    }

    override fun <T> onClick(model: T) {
        model as ProductResponse
         sharedViewModel.setLiveDataValue(model)
        findNavController().navigate(R.id.action_productFragment_to_prdouctDetailsFragment)
    }
}