package com.merqueo.co.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.merqueo.co.home.R
import com.merqueo.co.home.databinding.FragmentHomeBinding
import com.merqueo.co.home.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    lateinit var content: View
    lateinit var homeBinding: FragmentHomeBinding


    val moviesViewModel: MovieViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        val mRootView = homeBinding.root

        homeBinding.lifecycleOwner = this

        return mRootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeBinding.viewModel = moviesViewModel
        moviesViewModel.getDataRemote()
        moviesViewModel.movieList.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "${it.size}", Toast.LENGTH_SHORT).show()
        })

    }


}