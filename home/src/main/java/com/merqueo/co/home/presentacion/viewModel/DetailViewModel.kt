package com.merqueo.co.home.presentacion.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.merqueo.co.core.SingleLiveEvent
import com.merqueo.co.home.domain.service.IserviceDetail
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
class DetailViewModel(
    private val iserviceDetail: IserviceDetail
) :
    ViewModel() {

    var job: Job? = null

    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()
    val movieChangeState = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()

    var movie = SingleLiveEvent<MovieItemDomain>()

    fun getMovie(idMovie: Int) {
        showLoading.set(true)
        job = coroutineScope.launch {
            val response = iserviceDetail.getMovie(idMovie)
            showLoading.set(false)
            withContext(Dispatchers.Main) {
                flowOf(response)
                    .catch { throwable ->
                        showError.value = throwable.localizedMessage
                    }.collect {
                        movie.value = it
                    }
            }
        }
    }


}