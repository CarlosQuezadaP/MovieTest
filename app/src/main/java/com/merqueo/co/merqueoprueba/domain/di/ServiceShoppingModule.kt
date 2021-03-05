package com.co.merqueo.shoppingcart.dominio.di

import com.merqueo.co.usecases.IServiceShoppingCart
import com.merqueo.co.usecases.ServiceShoppingCart
import org.koin.dsl.module

val moduleServiceShopping = module {
    single<com.merqueo.co.usecases.IServiceShoppingCart> {
        com.merqueo.co.usecases.ServiceShoppingCart(
            get()
        )
    }
}