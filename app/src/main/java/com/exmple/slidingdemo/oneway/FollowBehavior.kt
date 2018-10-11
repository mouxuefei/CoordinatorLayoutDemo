package com.exmple.slidingdemo.oneway

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView

class FollowBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<TextView>(context, attrs) {

    /**
     * 判断child的布局是否依赖 dependency
     *
     * 根据逻辑来判断返回值，返回 false 表示不依赖，返回 true 表示依赖
     *
     * 在一个交互行为中，Dependent View 的变化决定了另一个相关 View 的行为。
     * 在这个例子中， Button 就是 Dependent View，因为 TextView 跟随着它。
     * 实际上 Dependent View 就相当于我们前面介绍的被观察者
     *
     */
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: TextView?, dependency: View?): Boolean {
        return dependency is Button
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: TextView?, dependency: View?): Boolean {
        if (dependency != null) {
            child?.x = dependency.x
            child?.y = dependency.y
        }
        return true
    }
}

