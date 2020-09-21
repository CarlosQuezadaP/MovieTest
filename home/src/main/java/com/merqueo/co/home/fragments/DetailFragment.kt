package com.merqueo.co.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.merqueo.co.home.R


class DetailFragment : Fragment() {

    lateinit var content: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        content = inflater.inflate(R.layout.fragment_detail, container, false)

        return content
    }


}