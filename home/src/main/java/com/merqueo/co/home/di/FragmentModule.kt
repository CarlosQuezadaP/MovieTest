package com.merqueo.co.home.di

import com.merqueo.co.home.fragments.HomeFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val homeFragmentModule = module {
    fragment { HomeFragment() }
}