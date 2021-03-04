package com.merqueo.co.merqueoprueba.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentDetailBinding
import com.merqueo.co.merqueoprueba.presentation.viewModel.DetailViewModel
import com.merqueo.co.models.ui.MovieItemDomain
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_detail_.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val detailMovieViewModel: DetailViewModel by viewModel()
    private lateinit var content: View
    private lateinit var mRootView: View

    private lateinit var movieItemDomain: MovieItemDomain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_, container, false
        )
        mRootView = fragmentDetailBinding.root
        fragmentDetailBinding.lifecycleOwner = this


        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailBinding.viewModel = detailMovieViewModel
        detailMovieViewModel.getMovie(args.movieID)

        detailMovieViewModel.movieChangeState.observe(viewLifecycleOwner, {
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

        mRootView.button_add.setOnClickListener {
            GlobalScope.launch {
                val movie = detailMovieViewModel.movie.value!!
                movie.onStore = true
                detailMovieViewModel.updateMovieState(movie)
            }
        }

        mRootView.button_delete.setOnClickListener {
            GlobalScope.launch {
                val movie = detailMovieViewModel.movie.value!!
                movie.onStore = false
                detailMovieViewModel.updateMovieState(movie)
            }
        }
    }


}