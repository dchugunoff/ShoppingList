package com.chugunov.shoppinglist.di

import android.app.Application
import com.chugunov.shoppinglist.data.AppDatabase
import com.chugunov.shoppinglist.data.ShopListDao
import com.chugunov.shoppinglist.data.ShopListRepositoryImpl
import com.chugunov.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}