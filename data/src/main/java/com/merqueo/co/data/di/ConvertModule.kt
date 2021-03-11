package com.merqueo.co.data.di

import com.merqueo.co.data.anticorruption.Converter
import com.merqueo.co.data.anticorruption.IConverter
import org.koin.dsl.module

val entityToDomainConverterModule = module {
    single<IConverter> { Converter() }
}