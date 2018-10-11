package com.exmple.slidingdemo.fourway

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager, var mFragments: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(arg0: Int): Fragment {
        return mFragments[arg0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

}