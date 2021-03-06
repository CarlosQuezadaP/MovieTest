package com.merqueo.co.merqueoprueba.domain.di

import com.merqueo.co.merqueoprueba.presentation.fragment.DetailFragment
import com.merqueo.co.merqueoprueba.presentation.fragment.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

@InternalCoroutinesApi
val homeFragmentModule = module {
    fragment { HomeFragment() }
}

@ExperimentalCoroutinesApi
val detailFragmentModule = module {
    fragment { DetailFragment() }
}