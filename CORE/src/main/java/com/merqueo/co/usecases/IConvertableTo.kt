package com.merqueo.co.usecases

interface IConvertableTo<T> {
    fun convertTo(): T?
}