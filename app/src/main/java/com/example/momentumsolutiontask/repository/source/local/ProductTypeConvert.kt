package com.example.momentumsolutiontask.repository.source.local

import androidx.room.TypeConverter
import com.example.momentumsolutiontask.pojo.ProductResponse
import com.example.momentumsolutiontask.pojo.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductTypeConverter {

    @TypeConverter
    fun fromList(list: List<ProductResponse>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(data: String?): List<ProductResponse>? {
        val listType = object : TypeToken<List<ProductResponse>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromObjectProduct(product: ProductResponse?): String? {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun toObjectProduct(data: String?):ProductResponse? {
        val listType = object : TypeToken<ProductResponse>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromObjectRating(rating: Rating): String? {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toObjectRating(data: String?):Rating? {
        val listType = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(data, listType)
    }
}