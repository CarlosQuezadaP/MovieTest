package com.co.merqueo.shoppingcart.dominio.di

import com.co.merqueo.shoppingcart.presentation.fragment.ShopFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module


val fragmentShoppingModule = module {
    fragment { ShopFragment() }
}