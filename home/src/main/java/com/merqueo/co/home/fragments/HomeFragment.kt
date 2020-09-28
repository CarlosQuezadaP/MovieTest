package com.merqueo.co.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.merqueo.co.home.R
import com.merqueo.co.home.adapter.MovieAdapter
import com.merqueo.co.home.databinding.FragmentHomeBinding
import com.merqueo.co.home.viewModel.MovieViewModel
import com.merqueo.co.models.dto.upcoming.MovieDto
import com.merqueo.co.provide.db.MerqueoDatabase
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var content: View
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mRootView: View
    private val moviesViewModel: MovieViewModel by viewModel()
    private val movieDao: MerqueoDatabase by inject()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        mRootView = homeBinding.root
        homeBinding.lifecycleOwner = this
        setView()

        return mRootView
    }


    private fun setView() {
        movieAdapter = MovieAdapter()
        mRootView.recyclerview.adapter = movieAdapter
    }


    private fun showData(movies: List<MovieDto>) {
        movieAdapter.submitList(movies)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeBinding.viewModel = moviesViewModel
        moviesViewModel.getDataRemote()
        moviesViewModel.movieList.observe(viewLifecycleOwner, {
            showData(it)
        })
    }


}