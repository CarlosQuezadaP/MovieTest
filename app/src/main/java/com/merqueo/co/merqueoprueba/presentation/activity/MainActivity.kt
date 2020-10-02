package com.merqueo.co.merqueoprueba.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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
    var menuItem: MenuItem? = null

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            setOf(
                R.id.homeFragment,
                R.id.shopFragment,
            )
        ).build()
    }

    private val navController: NavController by lazy { findNavController(R.id.fragment_nav_host) }


    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding!!.root)

        setSupportActionBar(tlb_main)
        setupActionBarWithNavController(navController, appBarConfiguration)
        button_navigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    button_navigation.visibility =
                        View.VISIBLE

                    showMenu(false)

                }
                R.id.shopFragment -> {
                    button_navigation.visibility =
                        View.VISIBLE

                    showMenu(true)


                }
                else -> button_navigation.visibility = View.GONE
            }
        }

        mainViewModel.totalCart.observe(this, {
            setupBadge(it)
        })


    }

    private fun showMenu(value: Boolean) {
        menuItem?.apply {
            isVisible = value
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menuItem = menu?.findItem(R.id.delete)
        showMenu(false)

        menuItem!!.setOnMenuItemClickListener {

            delete()
            return@setOnMenuItemClickListener true
        }
        return true
    }

    private fun delete() {
        mainViewModel.deleteAll()
    }


    private fun setupBadge(count: Int) {

        mainBinding!!.buttonNavigation.getOrCreateBadge(R.id.nav_store).apply {
            number = count
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }


}