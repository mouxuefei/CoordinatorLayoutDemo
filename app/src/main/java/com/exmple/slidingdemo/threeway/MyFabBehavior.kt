package com.exmple.slidingdemo.threeway

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View

class MyFabBehavior//1. 必须重写两个参数的构造方法, 因为behavior的实例化�是反射这个构造方法实现的
(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    protected lateinit var mBottomAnim: MyBehaviorAnim
    private var isHide: Boolean = false
    private val canScroll = true
    private var mTotalScrollY: Int = 0
    protected var isInit = true //防止new Anim导致的parent 和child坐标变化

    private val mDuration = 400
    private val mInterpolator = LinearOutSlowInInterpolator()
    private val minScrollY = 5//触发滑动动画最小距离
    private val scrollYDistance = 40//设置最小滑动距离

    //2. 关心谁
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }

    /**
     * 触发滑动嵌套滚动之前调用的方法
     *
     * @param coordinatorLayout coordinatorLayout父布局
     * @param child             使用Behavior的子View
     * @param target            触发滑动嵌套的View(实现NestedScrollingChild接口)
     * @param dx                滑动的X轴距离
     * @param dy                滑动的Y轴距离
     * @param consumed          父布局消费的滑动距离，consumed[0]和consumed[1]代表X和Y方向父布局消费的距离，默认为0
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View,
                                   dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
    }

    /**
     * 滑动嵌套滚动时触发的方法
     *
     * @param coordinatorLayout coordinatorLayout父布局
     * @param child             使用Behavior的子View
     * @param target            触发滑动嵌套的View
     * @param dxConsumed        TargetView消费的X轴距离
     * @param dyConsumed        TargetView消费的Y轴距离
     * @param dxUnconsumed      未被TargetView消费的X轴距离
     * @param dyUnconsumed      未被TargetView消费的Y轴距离(如RecyclerView已经到达顶部或底部，而用户继续滑动，此时dyUnconsumed的值不为0，可处理一些越界事件)
     */
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View,
                                dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (canScroll) {
            mTotalScrollY += dyConsumed
            if (Math.abs(dyConsumed) > minScrollY || Math.abs(mTotalScrollY) > scrollYDistance) {
                if (dyConsumed < 0) {
                    if (isHide) {
                        mBottomAnim.show()
                        isHide = false
                    }
                } else if (dyConsumed > 0) {
                    if (!isHide) {
                        mBottomAnim.hide()
                        isHide = true
                    }
                }
                mTotalScrollY = 0
            }
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        if (isInit) {
            mBottomAnim = MyBehaviorAnim(child)
            isInit = false
        }
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View) {
        super.onStopNestedScroll(coordinatorLayout, child, target)
    }

}

