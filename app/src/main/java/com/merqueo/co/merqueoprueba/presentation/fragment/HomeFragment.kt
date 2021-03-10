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
import com.merqueo.co.merqueoprueba.handlers.AddRemoveListener
import com.merqueo.co.merqueoprueba.handlers.ClickListener
import com.merqueo.co.merqueoprueba.handlers.IResearch
import com.merqueo.co.merqueoprueba.presentation.adapter.MovieAdapter
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
import com.merqueo.co.merqueoprueba.utils.navigateUriWithDefaultOptions
import com.merqueo.co.merqueoprueba.utils.setExitToFullScreenTransition
import com.merqueo.co.merqueoprueba.utils.setReturnFromFullScreenTransition
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(), AddRemoveListener, ClickListener, IResearch {

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

        homeBinding.apply {
            lifecycleOwner = this@HomeFragment
            research = this@HomeFragment
            mRootView = this.root
            viewModel = moviesViewModel
        }


        setupAdapter()

        moviesViewModel.showData().observe(viewLifecycleOwner, { movieState ->
            showData(movieState.data)
        })



        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setExitToFullScreenTransition()
        setReturnFromFullScreenTransition()
        setupAdapter()
    }


    private fun setupAdapter() {
        movieAdapter = MovieAdapter(this, this)


        mRootView.recyclerViewMovies.apply {
            adapter = movieAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }


    private fun showData(movies: List<MovieItemDomain>) {
        movieAdapter.apply {
            submitList(movies)
            notifyDataSetChanged()
        }
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


    override fun research() {
        moviesViewModel.showData()
    }


}