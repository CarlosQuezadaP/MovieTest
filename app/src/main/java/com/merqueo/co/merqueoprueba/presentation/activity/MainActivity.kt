package com.merqueo.co.merqueoprueba.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.merqueo.co.merqueoprueba.R
import com.merqueo.co.merqueoprueba.databinding.ActivityMainBinding
import com.merqueo.co.merqueoprueba.presentation.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    var mainBinding: ActivityMainBinding? = null

    private val mainViewModel: MainViewModel by viewModel()

    private val navController: NavController by lazy { findNavController(R.id.fragment_nav_host) }


    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding!!.root)

        bottom_navigation.setupWithNavController(navController)


        mainViewModel.totalCart.observe(this, {
            setupBadge(it)
        })
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }

    private fun setupBadge(count: Int) {
        bottom_navigation.getOrCreateBadge(R.id.shopFragment2).apply {
            number = count
        }
    }

}