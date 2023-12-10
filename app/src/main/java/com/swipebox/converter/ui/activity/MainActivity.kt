package com.swipebox.converter.ui.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swipebox.converter.R
import com.swipebox.converter.base.BaseActivity
import com.swipebox.converter.base.BaseViewModel
import com.swipebox.converter.ui.adapter.ViewPagerAdapter
import com.swipebox.converter.viewModel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: CurrencyConverterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabPositionTitle(position)
        }.attach()

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    private fun getTabPositionTitle(position: Int): String {
        if (position == 0) return "RATE" else return "CHARTS"
    }
}