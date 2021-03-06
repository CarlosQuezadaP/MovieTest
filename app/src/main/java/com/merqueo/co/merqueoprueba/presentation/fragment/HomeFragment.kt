package com.merqueo.co.merqueoprueba.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentHomeBinding
import com.merqueo.co.merqueoprueba.presentation.viewModel.MovieViewModel
import com.merqueo.co.merqueoprueba.util.OnClick
import com.merqueo.co.usecases.presentacion.navigateUriWithDefaultOptions
import com.merqueo.co.usecases.util.AddRemoveListener
import com.merqueo.co.usecases.util.ClickListener
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(), AddRemoveListener, ClickListener, OnClick {

    private lateinit var content: View
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mRootView: View
    private val moviesViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: com.merqueo.co.merqueoprueba.presentation.adapter.MovieAdapter


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
        movieAdapter = com.merqueo.co.merqueoprueba.presentation.adapter.MovieAdapter(this, this)
        mRootView.recyclerview.adapter = movieAdapter
    }


    private fun showData(movies: List<com.merqueo.co.domain.models.MovieItemDomain>) {
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
                Toasty.success(
                    requireActivity(),
                    getString(R.string.text_homeFragment_changeStatus)
                ).show()
            } else {
                Toasty.error(
                    requireActivity(),
                    getString(R.string.text_home_Fragment_noChangeState)
                )
                    .show()
            }
        })
    }


    override fun onClick(movieItemDomain: com.merqueo.co.domain.models.MovieItemDomain) {
        findNavController().navigateUriWithDefaultOptions(
            Uri.parse("merqueoMovie://moviedetails/${movieItemDomain.id}")
        )
    }

    override fun onItemClickOnButton(
        movieItemDomain: com.merqueo.co.domain.models.MovieItemDomain,
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