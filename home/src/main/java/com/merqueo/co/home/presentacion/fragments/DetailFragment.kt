package com.merqueo.co.home.presentacion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.merqueo.co.home.R
import com.merqueo.co.home.databinding.FragmentDetailBinding
import com.merqueo.co.home.presentacion.viewModel.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val detailMovieViewModel: DetailViewModel by viewModel()
    private lateinit var content: View
    private lateinit var mRootView: View

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
    }


}