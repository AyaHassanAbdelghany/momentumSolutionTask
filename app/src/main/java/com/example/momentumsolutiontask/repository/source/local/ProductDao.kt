package com.example.momentumsolutiontask.repository.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.momentumsolutiontask.pojo.ProductResponse

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productsList: List<ProductResponse>)

    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductResponse>


    @Query("DELETE FROM products")
    suspend fun deleteProducts() :Int
}