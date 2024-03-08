package com.example.momentumsolutiontask.di

import android.content.Context
import androidx.room.Room
import com.example.momentumsolutiontask.utils.PRODUCT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductDataBase {
        return Room.databaseBuilder(
            context,
            ProductDataBase::class.java,
            PRODUCT_DATABASE
        ).build()
    }

    @Provides
    fun provideProductDao(database: ProductDataBase): ProductDao {
        return database.productDao()
    }
}