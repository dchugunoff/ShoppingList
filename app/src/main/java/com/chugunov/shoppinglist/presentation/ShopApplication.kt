package com.chugunov.shoppinglist.presentation

import android.app.Application
import com.chugunov.shoppinglist.di.DaggerApplicationComponent

class ShopApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}