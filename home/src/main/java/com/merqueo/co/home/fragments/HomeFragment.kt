package com.merqueo.co.home.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.merqueo.co.core.navigateUriWithDefaultOptions
import com.merqueo.co.home.AddRemoveListener
import com.merqueo.co.home.ClickListener
import com.merqueo.co.home.R
import com.merqueo.co.home.adapter.MovieAdapter
import com.merqueo.co.home.databinding.FragmentHomeBinding
import com.merqueo.co.home.viewModel.MovieViewModel
import com.merqueo.co.models.ui.MovieItemDomain
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(), AddRemoveListener, ClickListener {

    private lateinit var content: View
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mRootView: View
    private val moviesViewModel: MovieViewModel by viewModel()
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
        movieAdapter = MovieAdapter(this, this)
        mRootView.recyclerview.adapter = movieAdapter
    }


    private fun showData(movies: List<MovieItemDomain>) {
        movieAdapter.submitList(movies)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeBinding.viewModel = moviesViewModel
        moviesViewModel.movieList.observe(viewLifecycleOwner, {
            showData(it)
        })
        moviesViewModel.movieChangeState.observe(viewLifecycleOwner, {
            if (it) {
                Toasty.success(requireActivity(), "se realizo el cambio de estado.").show()
            } else {

                Toasty.error(requireActivity(), "No es posible realizar este cambio de estado")
                    .show()
            }
        })
    }


    override fun onClick(movieItemDomain: MovieItemDomain) {

        findNavController().navigateUriWithDefaultOptions(
            Uri.parse("merqueoMovie://moviedetails/${movieItemDomain.id}")
        )
    }

    override fun onItemClickOnButton(movieItemDomain: MovieItemDomain, type: Boolean) {
        movieItemDomain.onStore = type
        GlobalScope.launch {
            moviesViewModel.updateMovieState(movieItemDomain)
        }
    }


}