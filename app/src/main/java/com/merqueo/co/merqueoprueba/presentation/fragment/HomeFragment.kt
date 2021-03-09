package com.merqueo.co.merqueoprueba.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentHomeBinding
import com.merqueo.co.merqueoprueba.presentation.adapter.MovieAdapter
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
import com.merqueo.co.merqueoprueba.util.OnClick
import com.merqueo.co.merqueoprueba.navigateUriWithDefaultOptions
import com.merqueo.co.merqueoprueba.util.AddRemoveListener
import com.merqueo.co.merqueoprueba.util.ClickListener
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(), AddRemoveListener, ClickListener, OnClick {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mRootView: View
    private val moviesViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        mRootView = homeBinding.root
        homeBinding.lifecycleOwner = this
        homeBinding.onclick = this
        setView()


        return mRootView
    }


    private fun setView() {
        movieAdapter = MovieAdapter(this, this)
        mRootView.recyclerviewMovies.adapter = movieAdapter
    }


    private fun showData(movies: List<MovieItemDomain>) {
        movieAdapter.submitList(movies)
        movieAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeBinding.viewModel = moviesViewModel

        moviesViewModel.movieList.observe(viewLifecycleOwner, {
            showData(it)
        })
    }


    override fun onClick(movieItemDomain: MovieItemDomain) {
        findNavController().navigateUriWithDefaultOptions(
            Uri.parse("merqueoMovie://moviedetails/${movieItemDomain.id}")
        )
    }

    override fun onItemClickOnButton(
        movieItemDomain: MovieItemDomain,
        type: Boolean
    ) {
        movieItemDomain.onStore = type
        GlobalScope.launch {
            moviesViewModel.updateMovieState(movieItemDomain)
        }
    }

    override fun onClick() {
        moviesViewModel.showData()
    }


}