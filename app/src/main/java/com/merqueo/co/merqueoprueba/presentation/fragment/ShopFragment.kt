package com.merqueo.co.merqueoprueba.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentShopBinding
import com.merqueo.co.merqueoprueba.presentation.adapter.StoreAdapter
import com.merqueo.co.merqueoprueba.presentation.viewModel.ViewModelShopping
import kotlinx.android.synthetic.main.fragment_shop.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    lateinit var shopBindingImpl: FragmentShopBinding
    private lateinit var mRootView: View
    private val viewModelShopping: ViewModelShopping by viewModel()


    private lateinit var movieAdapter: StoreAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shopBindingImpl = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop, container, false
        )
        mRootView = shopBindingImpl.root
        shopBindingImpl.lifecycleOwner = this
        setView()
        return mRootView
    }

    private fun setView() {
        movieAdapter = StoreAdapter()
        mRootView.recyclerview.adapter = movieAdapter
    }


    private fun showData(movies: List<com.merqueo.co.domain.models.MovieItemDomain>) {
        movieAdapter.submitList(movies)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelShopping.movieList.observe(viewLifecycleOwner, {
            showData(it)
        })


    }


}