package com.merqueo.co.merqueoprueba

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.fragment.android.setupKoinFragmentFactory


class MainActivity : AppCompatActivity() {

    lateinit var textCartItemCount: TextView
    var mCartItemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem: MenuItem = menu.findItem(R.id.action_shopping)
        val actionView: View = menuItem.actionView

        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView
        setupBadge()
        actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_shopping -> {
                Toast.makeText(this@MainActivity, "Action", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBadge() {
        textCartItemCount.text = "0"
    }


}