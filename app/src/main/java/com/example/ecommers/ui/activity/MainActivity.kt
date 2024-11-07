package com.example.ecommers.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.ecommers.R
import com.example.ecommers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        showMainContent()

    }

    private fun showMainContent() {
        val host = NavHostFragment.create(R.navigation.main_nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container, host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }
}