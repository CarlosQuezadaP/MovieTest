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
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    lateinit var content: View
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var mRootView: View


    val moviesViewModel: MovieViewModel by viewModel()
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