package com.hx.hub.ui.main

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.hx.architecture.core.adapter.ViewPagerAdapter
import com.hx.architecture.core.base.view.fragment.BaseFragment
import com.hx.hub.R
import com.hx.hub.ui.main.home.HomeFragment
import com.hx.hub.ui.main.profile.ProfileFragment
import com.hx.hub.ui.main.repos.ReposFragment
import com.hx.hub.ui.main.trending.TrendingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@Suppress("PLUGIN_WARNING")
@SuppressLint("CheckResult")
@AndroidEntryPoint
class MainFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_main

    private val mViewModel: MainViewModel by viewModels()     // not used

    private var isPortMode: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isPortMode = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        viewPager.adapter = ViewPagerAdapter(childFragmentManager,
                listOf(HomeFragment(), ReposFragment(), TrendingFragment(),  ProfileFragment()))
        viewPager.offscreenPageLimit = 3

        when (isPortMode) {
            true -> bindsPortScreen()
            false -> bindsLandScreen()
        }
    }

    private fun bindsPortScreen() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) = onPageSelectChanged(position)


            override fun onPageScrollStateChanged(state: Int) = Unit
        })
        navigation.setOnNavigationItemSelectedListener { menuItem ->
            onBottomNavigationSelectChanged(menuItem)
            true
        }
    }

    private fun bindsLandScreen() {
        fabHome.setOnClickListener { onPageSelectChanged(0) }
        fabRepo.setOnClickListener { onPageSelectChanged(1) }
        fabTrending.setOnClickListener { onPageSelectChanged(2) }
        fabProfile.setOnClickListener { onPageSelectChanged(3) }
    }

    private fun onPageSelectChanged(index: Int) {
        // port-mode
        if (isPortMode) {
            for (position in 0..index) {
                if (navigation.visibility == View.VISIBLE)
                    navigation.menu.getItem(position).isChecked = index == position
            }
        } else {
            // land-mode
            if (viewPager.currentItem != index) {
                viewPager.currentItem = index
                if (fabMenu != null && fabMenu.isExpanded)
                    fabMenu.toggle()
            }
        }
    }

    // port-mode only
    private fun onBottomNavigationSelectChanged(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                viewPager.currentItem = 0
            }
            R.id.nav_repos -> {
                viewPager.currentItem = 1
            }
            R.id.nav_trending -> {
                viewPager.currentItem = 2
            }
            R.id.nav_profile -> {
                viewPager.currentItem = 3
            }
        }
    }
}
