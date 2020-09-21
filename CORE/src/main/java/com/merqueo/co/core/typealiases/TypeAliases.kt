package com.merqueo.co.core.typealiases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.merqueo.co.core.models.SuperResult
import kotlinx.coroutines.flow.Flow

typealias UnitHandler = () -> Unit
typealias InHandler<T> = (T) -> Unit
typealias OutHandler<T> = () -> T
typealias InSameOutHandler<T> = (T) -> T
typealias InOutHandler<T, R> = (T) -> R


typealias SUnitHandler = suspend () -> Unit
typealias SInHandler<T> = suspend (T) -> Unit
typealias SOutHandler<T> = suspend () -> T
typealias SInSameOutHandler<T> = suspend (T) -> T
typealias SInOutHandler<T, R> = suspend (T) -> R
typealias SParentInOutHandler<P, T, R> = suspend P.(T) -> R

typealias ResultLiveData<T> = LiveData<SuperResult<T>>
typealias ResultListLiveData<T> = LiveData<SuperResult<List<T>>>
typealias ResultMutableLiveData<T> = MutableLiveData<SuperResult<T>>
typealias PagedLiveData<T> = LiveData<PagedList<T>>

typealias AnyResult = SuperResult<Any>
typealias AnyResultLiveData = ResultLiveData<Any>
typealias AnyResultListLiveData = ResultListLiveData<Any>
typealias AnyResultMutableLiveData = ResultMutableLiveData<Any>

typealias ResultList<T> = SuperResult<List<T>>
typealias FlowResultList<T> = Flow<SuperResult<List<T>>>
typealias ResultInHandler<T> = (SuperResult<T>) -> Unit
typealias ResultInOutHandler<T, R> = (SuperResult<T>) -> SuperResult<R>
