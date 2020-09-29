package com.merqueo.co.home.di

import com.merqueo.co.home.fragments.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val homeFragmentModule = module {
    fragment { HomeFragment() }
}