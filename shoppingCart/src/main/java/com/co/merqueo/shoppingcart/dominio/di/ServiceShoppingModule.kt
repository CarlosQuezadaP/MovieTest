package com.co.merqueo.shoppingcart.dominio.di

import com.co.merqueo.shoppingcart.dominio.service.IServiceShoppingCart
import com.co.merqueo.shoppingcart.dominio.service.ServiceShoppingCart
import org.koin.dsl.module

val moduleServiceShopping = module {
    single<IServiceShoppingCart> { ServiceShoppingCart(get()) }
}