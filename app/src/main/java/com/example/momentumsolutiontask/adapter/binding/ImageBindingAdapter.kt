package com.example.momentumsolutiontask.adapter.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.momentumsolutiontask.R


class ImageBindingAdapter {
    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {

            Glide.with(imageView)
                .load(imageUrl)
                .error(R.drawable.ic_loading)
                .placeholder(R.drawable.ic_loading)
                .into(imageView)
        }


}
}