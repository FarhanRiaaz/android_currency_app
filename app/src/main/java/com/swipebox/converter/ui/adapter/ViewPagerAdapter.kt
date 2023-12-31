package com.swipebox.converter.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swipebox.converter.ui.fragment.RateChartFragment
import com.swipebox.converter.ui.fragment.RateListFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RateListFragment()
            1 -> RateChartFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}