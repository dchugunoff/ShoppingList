package com.chugunov.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.shoppinglist.domain.DeleteShopItemUseCase
import com.chugunov.shoppinglist.domain.EditShopItemUseCase
import com.chugunov.shoppinglist.domain.GetShopListUseCase
import com.chugunov.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}