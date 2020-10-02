package com.co.merqueo.shoppingcart.presentation.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co.merqueo.shoppingcart.dominio.service.IServiceShoppingCart
import com.merqueo.co.models.ui.MovieItemDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelShopping(private val iServiceShoppingCart: IServiceShoppingCart) : ViewModel() {


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
                flowOf(response)
                    .catch { throwable ->

                    }.collect { result ->
                        result.collect {
                            movieList.value = it
                        }
                    }
            }
        }
    }

}