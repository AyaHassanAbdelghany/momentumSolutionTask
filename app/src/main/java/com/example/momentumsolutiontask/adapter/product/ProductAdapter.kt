package com.example.momentumsolutiontask.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momentumsolutiontask.R
import com.example.momentumsolutiontask.adapter.OnClickListener
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
            binding.item = products
            itemView.setOnClickListener {
                listener.onClick(products)

            }
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