package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.usecases.presentacion.SingleLiveEvent
import com.merqueo.co.merqueoprueba.domain.servicio.IServiceMovie
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


    val movieChangeState = SingleLiveEvent<Boolean>()
    var movieList = MutableLiveData<List<MovieItemDomain>>()

    val showLoading = ObservableBoolean()
    val show = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()

    private var job: Job? = null
    private var coroutineScope = CoroutineScope(Dispatchers.IO)


    init {
        showData()
    }

    override fun showData() {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = iServiceMovie.getMovies()
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                flowOf(response)

                    .catch {
                        var error = this
                    }
                    .collect {
                        it.collect {
                            if (it.size == 0) {
                                show.value = true
                                showError.value = "The movie List is Empty"
                            } else {
                                show.value = false
                                showError.value = null
                                movieList.value = it
                            }
                        }
                }
            }
        }
    }

    suspend fun updateMovieState(movieItemDomain: MovieItemDomain) {
        coroutineScope.launch {
            val value = iServiceMovie.updateMovieState(movieItemDomain.id, movieItemDomain.onStore)
            withContext(Dispatchers.Main) {
                movieChangeState.value = value
            }
        }
    }


}