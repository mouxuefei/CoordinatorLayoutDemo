package com.exmple.slidingdemo

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusheight()
        initViewPager()
        initListner()
    }

    private fun initListner() {
        appBarLayout.addOnOffsetChangedListener { a, verticalOffset ->
            val measuredHeight = appBarLayout.measuredHeight
            val currentHeight = Math.abs(verticalOffset)
            var alpha = currentHeight * 2f / measuredHeight
            if (alpha > 1f) {
                alpha = 1f
            }
            act_main_fixed_tab.alpha = alpha
            act_main_ll_tab.alpha = alpha
            if (1 - alpha >= 0.5f) {
                layout_titleBar_main_tvTitle.alpha = 1 - alpha
            } else {
                layout_titleBar_main_tvTitle.alpha = 0f
            }
            layout_titleBar_main_ivRight.alpha = 1 - alpha
            act_main_scroll_tab.alpha = 1 - alpha
        }

    }

    private fun setStatusheight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        act_main_statusBar.layoutParams = params
        act_main_statusBar2.layoutParams = params
    }

    fun getStatusBarHeight(): Int {
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) {
            resources.getDimensionPixelSize(resId)
        } else 0
    }

    private fun initViewPager() {
        val fragmentList = ArrayList<Fragment>()
        val pairNew = Pair("type", "new")
        val pairHot = Pair("type", "old")
        fragmentList.add(newFragment<TestFragment>(pairNew))
        fragmentList.add(newFragment<TestFragment>(pairHot))

        val fragmentAdapter = FragmentAdapter(fragmentManager = supportFragmentManager, mFragments = fragmentList)
        vp.adapter = fragmentAdapter
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true  //ture 即标题平分屏幕宽度的模式
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                val simplePagerTitleView = SimplePagerTitleView(this@MainActivity)//设置为可渐变的View
                if (p1 == 0) {
                    simplePagerTitleView.text = "最新"
                } else {
                    simplePagerTitleView.text = "热门"
                }
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.normalColor = Color.parseColor("#676463")
                simplePagerTitleView.selectedColor = ContextCompat.getColor(this@MainActivity, R.color.barcolor)
                simplePagerTitleView.setOnClickListener { vp.currentItem = p1 }
                return simplePagerTitleView
            }

            override fun getCount() = 2

            override fun getIndicator(p0: Context?): IPagerIndicator {
                val linePagerIndicator = LinePagerIndicator(this@MainActivity)
                linePagerIndicator.setColors(ContextCompat.getColor(this@MainActivity, R.color.barcolor))//线的颜色
                linePagerIndicator.mode = 2
                linePagerIndicator.lineHeight = UIUtil.dip2px(this@MainActivity, 2.0).toFloat()
                linePagerIndicator.lineWidth = UIUtil.dip2px(this@MainActivity, 15.0).toFloat()
                linePagerIndicator.roundRadius = UIUtil.dip2px(this@MainActivity, 1.0).toFloat()
                return linePagerIndicator
            }
        }
        act_main_fixed_tab.navigator = commonNavigator
        ViewPagerHelper.bind(act_main_fixed_tab, vp)

        val commonNavigator2 = CommonNavigator(this)
        commonNavigator2.isAdjustMode = true  //ture 即标题平分屏幕宽度的模式
        commonNavigator2.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                val simplePagerTitleView = SimplePagerTitleView(this@MainActivity)//设置为可渐变的View
                if (p1 == 0) {
                    simplePagerTitleView.text = "最新"
                } else {
                    simplePagerTitleView.text = "热门"
                }
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.normalColor = ContextCompat.getColor(this@MainActivity, R.color._BCBCBC)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(this@MainActivity, R.color.white)
                simplePagerTitleView.setOnClickListener { vp.currentItem = p1 }
                return simplePagerTitleView
            }

            override fun getCount() = 2

            override fun getIndicator(p0: Context?): IPagerIndicator? {
                val indicator = MyWrapPagerIndicator(this@MainActivity)
                indicator.setFillColor(Color.parseColor("#60C1B8"), Color.parseColor("#31A99E"))
                return indicator
            }

        }
        act_main_scroll_tab.navigator = commonNavigator2
        ViewPagerHelper.bind(act_main_scroll_tab, vp)
    }
}
