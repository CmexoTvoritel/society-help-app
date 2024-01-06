package com.example.societyhelpapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.societyhelpapp.R
import com.example.societyhelpapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavGraph()
        setupToolbar()
        setupBottomBar()
    }

    private fun setupNavGraph() = binding.apply {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.informationFragment -> {
                    amBottomNavigation.visibility = View.GONE
                }
                R.id.mainFragment -> {
                    amBottomNavigation.visibility = View.VISIBLE
                }
                R.id.settingsFragment -> {
                    amBottomNavigation.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.constitutionFragment -> {
                    amBottomNavigation.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.constitutionInfoFragment -> {
                    amBottomNavigation.visibility = View.GONE
                }
            }
        }
    }

    private fun setupToolbar() = binding.apply {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        amToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomBar() = binding.apply {
        amBottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}