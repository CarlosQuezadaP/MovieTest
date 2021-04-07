package com.merqueo.co.merqueoprueba.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentDetailBinding
import com.merqueo.co.merqueoprueba.handlers.AddRemoveListener
import com.merqueo.co.merqueoprueba.presentation.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(), AddRemoveListener {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val detailMovieViewModel: DetailViewModel by viewModel()
    private lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_, container, false
        )


        fragmentDetailBinding.apply {
            mRootView = this.root
            lifecycleOwner = this@DetailFragment
            movieClickInterface = this@DetailFragment
            viewModel = detailMovieViewModel
        }


        detailMovieViewModel.getMovie(args.movieID)

        return mRootView
    }


    override fun onItemClickOnButton(
        movieItemDomain: MovieItemDomain,
        type: Boolean
    ) {
        movieItemDomain.onStore = type
        detailMovieViewModel.updateMovieState(movieItemDomain)
    }


}