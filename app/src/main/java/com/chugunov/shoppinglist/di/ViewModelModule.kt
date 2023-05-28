package com.chugunov.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.chugunov.shoppinglist.presentation.MainViewModel
import com.chugunov.shoppinglist.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}