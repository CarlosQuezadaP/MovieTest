package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merqueo.co.merqueoprueba.domain.servicio.IServiceMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val iServiceMain: IServiceMain) : ViewModel() {

    val totalCart = MutableLiveData<Int>()
    var coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        getCountStore()
    }

    fun getCountStore() {
        coroutineScope.launch {
            val response = iServiceMain.getCountStoreCart()
            withContext(Dispatchers.Main) {
                flowOf(response)
                    .catch { throwable ->

                    }.collect { result ->
                        result.collect {
                            totalCart.value = it
                        }
                    }
            }
        }
    }

    fun deleteAll() {
        coroutineScope.launch {
           iServiceMain.deleteAll()
        }
    }


}