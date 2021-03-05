package com.merqueo.co.data.di

import com.merqueo.co.data.anticorruption.EntityToDomainConverter
import com.merqueo.co.data.anticorruption.IEntityToDomainConverter
import org.koin.dsl.module

val entityToDomainConverterModule = module {
    single<IEntityToDomainConverter> { EntityToDomainConverter() }
}