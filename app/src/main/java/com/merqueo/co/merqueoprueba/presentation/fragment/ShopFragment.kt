package com.merqueo.co.merqueoprueba.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.merqueo.co.domain.models.MovieItemDomain
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.FragmentShopBinding
import com.merqueo.co.merqueoprueba.handlers.IDeleteAll
import com.merqueo.co.merqueoprueba.presentation.adapter.StoreAdapter
import com.merqueo.co.merqueoprueba.presentation.states.MovieViewState
import com.merqueo.co.merqueoprueba.presentation.viewModel.ViewModelShopping
import com.merqueo.co.merqueoprueba.utils.setExitToFullScreenTransition
import com.merqueo.co.merqueoprueba.utils.setReturnFromFullScreenTransition
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment(), IDeleteAll {

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

        shopBindingImpl.apply {
            lifecycleOwner = this@ShopFragment
            deleteAll = this@ShopFragment
        }

        mRootView = shopBindingImpl.root

        setupAdapter()

        return mRootView
    }


    private fun showData(movies: List<MovieItemDomain>) {
        movieAdapter.submitList(movies)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setExitToFullScreenTransition()
        setReturnFromFullScreenTransition()

        viewModelShopping.getFromLocal().observe(viewLifecycleOwner, {
            when (it) {
                is MovieViewState -> {
                    showData(it.data)
                }
            }
        })
    }

    private fun setupAdapter() {
        movieAdapter = StoreAdapter()
        mRootView.recyclerviewShop.apply {
            adapter = movieAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

    }

    override fun deleteAll() {
        viewModelShopping.deleteAll()
    }


}