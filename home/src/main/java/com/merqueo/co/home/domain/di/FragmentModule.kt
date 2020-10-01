package com.merqueo.co.home.domain.di

import com.merqueo.co.home.presentacion.fragments.DetailFragment
import com.merqueo.co.home.presentacion.fragments.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val homeFragmentModule = module {
    fragment { HomeFragment() }
}

val detailFragmentModule = module {
    fragment { DetailFragment() }
}