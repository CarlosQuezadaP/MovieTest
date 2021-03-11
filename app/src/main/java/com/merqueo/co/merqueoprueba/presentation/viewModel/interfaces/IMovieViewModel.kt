package com.merqueo.co.merqueoprueba.presentation.viewModel.interfaces

import androidx.lifecycle.LiveData
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState

interface IMovieViewModel {
    fun showData() : LiveData<MovieViewState>
}