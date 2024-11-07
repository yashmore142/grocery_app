package com.example.ecommers.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.ecommers.R
import com.example.ecommers.databinding.FragmentDashBoardBinding


class DashBoardFragment : Fragment() {

    private lateinit var mBinding: FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDashBoardBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentChange(HomeFragment(), HomeFragment::class.java.name)
        mBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragmentChange(HomeFragment(), HomeFragment::class.java.name)
                    true
                }

                R.id.navigation_cart -> {
                    fragmentChange(CartFragment(), CartFragment::class.java.name)
                    true
                }

                R.id.navigation_profile -> {
                    fragmentChange(HistoryFragment(), HistoryFragment::class.java.name)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun fragmentChange(fragment: Fragment, tag: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.nav_dashboard_container, fragment, tag)
            .commit()
    }

}