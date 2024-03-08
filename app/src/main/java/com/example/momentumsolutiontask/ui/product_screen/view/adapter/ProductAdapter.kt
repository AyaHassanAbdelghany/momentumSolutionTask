package com.example.momentumsolutiontask.ui.product_screen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momentumsolutiontask.R
import com.example.momentumsolutiontask.databinding.ItemProductBinding
import com.example.momentumsolutiontask.pojo.ProductResponse


class ProductAdapter(
    var listener: OnClickListener,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<ProductResponse>() {
        override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductResponse,
            newItem: ProductResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)


    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(products: ProductResponse) {
            setData(products)
            itemView.setOnClickListener {
                listener.onClick(products)

            }
        }

        private fun setData(products: ProductResponse){
            Glide.with(binding.productImage)
                .load(products.image)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
                .into(binding.productImage)

            binding.productTitleTxt.text = products.title
            binding.productDescTxt.text = products.description
            binding.productPriceTxt.text = products.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)

    }
}