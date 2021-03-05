package com.merqueo.co.merqueoprueba.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val iServiceMain: com.merqueo.co.usecases.IServiceMain) : ViewModel() {

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
                    .collect {
                        it.collect {
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