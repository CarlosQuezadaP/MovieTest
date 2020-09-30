package com.merqueo.co.merqueoprueba

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.merqueo.co.merqueoprueba.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    var mainBinding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModel()

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            setOf(
                R.id.homeFragment,
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


        btm_nav_main.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> btm_nav_main.visibility =
                    View.VISIBLE
                else -> btm_nav_main.visibility = View.GONE
            }
        }

        mainViewModel.totalCart.observe(this, {
            setupBadge(it)
        })

    }

    private fun setupBadge(count: Int) {
        mainBinding!!.btmNavMain.getOrCreateBadge(R.id.nav_store).apply {
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