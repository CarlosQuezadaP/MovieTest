package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelShopping(private val iServiceShoppingCart: com.merqueo.co.merqueoprueba.domain.servicio.IServiceShoppingCart) :
    ViewModel() {


    var coroutineScope = CoroutineScope(Dispatchers.IO)
    val showLoading = ObservableBoolean()
    var movieList = MutableLiveData<List<MovieItemDomain>>()

    init {
        getFromLocal()
    }

    private fun getFromLocal() {
        coroutineScope.launch {
            val response = iServiceShoppingCart.getAllStore()
            withContext(Dispatchers.Main) {
                response.collect {
                    movieList.value = it
                }

            }
        }
    }

}