package com.merqueo.co.merqueoprueba

import androidx.lifecycle.LiveData

interface ISetupBadge {
    fun updateValue(cont: LiveData<Int>)
}
