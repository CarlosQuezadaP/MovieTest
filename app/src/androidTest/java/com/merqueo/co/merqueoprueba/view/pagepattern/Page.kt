package com.merqueo.co.merqueoprueba.view.pagepattern

open class Page {
    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        return this
    }


}