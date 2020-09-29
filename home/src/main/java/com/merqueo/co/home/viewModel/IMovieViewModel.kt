package com.merqueo.co.home.viewModel

interface IMovieViewModel {
    fun getDataRemote()
    suspend fun getDataLocal()
}