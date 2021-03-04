package com.co.merqueo.shoppingcart.dominio.di

import com.merqueo.co.merqueoprueba.domain.servicio.IServiceShoppingCart
import com.merqueo.co.merqueoprueba.domain.servicio.ServiceShoppingCart
import org.koin.dsl.module

val moduleServiceShopping = module {
    single<IServiceShoppingCart> { ServiceShoppingCart(get()) }
}