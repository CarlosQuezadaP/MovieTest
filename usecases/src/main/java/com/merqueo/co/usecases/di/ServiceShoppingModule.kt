package com.co.merqueo.shoppingcart.dominio.di

import com.merqueo.co.usecases.usecases.IServiceShoppingCart
import com.merqueo.co.usecases.usecases.ServiceShoppingCart
import org.koin.dsl.module

val moduleServiceShopping = module {
    single<IServiceShoppingCart> {
        ServiceShoppingCart(
            get()
        )
    }
}