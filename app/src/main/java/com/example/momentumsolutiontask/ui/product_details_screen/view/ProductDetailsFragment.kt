package com.example.momentumsolutiontask.ui.product_details_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.momentumsolutiontask.R
import com.example.momentumsolutiontask.databinding.FragmentPrdouctDetailsBinding
import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.ui.product_screen.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPrdouctDetailsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrdouctDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.toolBar.arrowBackImage.visibility = View.VISIBLE
        binding.toolBar.arrowBackImage.setOnClickListener {
            findNavController().popBackStack()
        }

        sharedViewModel.product.observe(viewLifecycleOwner){
            setData(it)
        }
    }

    private fun setData(productResponse: ProductResponse){
        Glide.with(binding.productImage)
            .load(productResponse.image)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
            .into(binding.productImage)

        binding.productTitleTxt.text = productResponse.title
        binding.productDescTxt.text = productResponse.description
        binding.productPriceTxt.text = productResponse.price.toString()
        binding.productRateTxt.text = productResponse.rating?.rate.toString()

    }
}