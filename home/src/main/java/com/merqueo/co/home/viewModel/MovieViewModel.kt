package com.merqueo.co.home.viewModel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.home.SingleLiveEvent
import com.merqueo.co.home.domain.IServiceMovie
import com.merqueo.co.infraestructura.source.remote.response.AppResult
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MovieViewModel(
    private val iServiceMovie: IServiceMovie
) :
    ViewModel(), IMovieViewModel {

    var job: Job? = null

    val excepcionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable -> onError("Exception: ${throwable.localizedMessage}") }
    var coroutineScope = CoroutineScope(Dispatchers.IO + excepcionHandler)
    val showLoading = ObservableBoolean()
    val totalCart = ObservableInt()
    val showError = SingleLiveEvent<String>()
    var movieList = MutableLiveData<List<MovieItemDomain>>()


    init {
        getDataRemote()
    }

    override fun getDataRemote() {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = iServiceMovie.getAllFromRemote()
            withContext(Dispatchers.Main) {
                showLoading.set(false)
                when (response) {
                    is AppResult.Success -> {
                        showError.value = null
                        val data = response.successData.movieDtos
                        val movies = data.map {
                            it.convertTo()
                        }
                        saveData(movieDtos = movies)
                    }
                    is AppResult.Error -> showError.value = response.exception.message
                }
            }
        }
    }


    private fun onError(message: String) {
        showError.value = message
    }

    suspend fun saveData(movieDtos: List<MovieItemDomain>) {
        coroutineScope.launch {
            iServiceMovie.saveMovies(movieDtos)
            withContext(Dispatchers.Main) {
                getDataLocal()
            }
        }
    }

    override suspend fun getDataLocal() {
        coroutineScope.launch {
            val response = iServiceMovie.getAllFromLocale()
            withContext(Dispatchers.Main) {
                flowOf(response)
                    .catch { throwable ->
                        showError.value = throwable.localizedMessage
                    }.collect { result ->
                        result.collect {
                            movieList.value = it
                        }
                    }
            }
        }
    }

    suspend fun updateMovieState(movieItemDomain: MovieItemDomain) {
        coroutineScope.launch {
            iServiceMovie.updateMovieState(movieItemDomain)
        }
    }




}