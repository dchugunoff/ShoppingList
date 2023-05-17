package com.chugunov.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.shoppinglist.data.ShopListRepositoryImpl
import com.chugunov.shoppinglist.data.ShopListRepositoryImpl.getShopList
import com.chugunov.shoppinglist.domain.DeleteShopItemUseCase
import com.chugunov.shoppinglist.domain.EditShopItemUseCase
import com.chugunov.shoppinglist.domain.GetShopListUseCase
import com.chugunov.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()



    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}