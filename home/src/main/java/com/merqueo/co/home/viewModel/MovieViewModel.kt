package com.merqueo.co.home.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merqueo.co.home.SingleLiveEvent
import com.merqueo.co.home.domain.IServiceMovie
import com.merqueo.co.infraestructura.source.remote.AppResult
import com.merqueo.co.models.dto.upcoming.MovieDto
import kotlinx.coroutines.launch


class MovieViewModel(
    private val iServiceMovie: IServiceMovie
) :
    ViewModel(), IMovieViewModel {


    val showLoading = ObservableBoolean()
    val showError = SingleLiveEvent<String>()
    var movieList = MutableLiveData<List<MovieDto>>()


    override fun getDataRemote() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = iServiceMovie.getAllMovies()

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    movieList.value = result.successData.movieDtos
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    override fun getDataLocal() {
    }

    override fun saveMoviesLocal() {

    }


}