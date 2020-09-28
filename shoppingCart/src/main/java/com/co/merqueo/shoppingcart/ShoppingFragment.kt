package com.co.merqueo.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class ShoppingFragment : Fragment() {

    private lateinit var content: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        content = inflater.inflate(R.layout.fragment_shopping, container, false)

        return content
    }

}