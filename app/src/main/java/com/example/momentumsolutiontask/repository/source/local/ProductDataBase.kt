package com.example.momentumsolutiontask.repository.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.momentumsolutiontask.pojo.ProductResponse

@Database(entities = [ProductResponse::class], version = 1)
@TypeConverters(ProductTypeConverter::class)
abstract class ProductDataBase: RoomDatabase() {

    abstract fun productDao(): ProductDao
}