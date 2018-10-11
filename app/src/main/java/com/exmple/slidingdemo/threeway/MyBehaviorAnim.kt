package com.exmple.slidingdemo.threeway

import android.animation.ValueAnimator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.View

class MyBehaviorAnim(private val mBottomView: View) {
    private val mOriginalY: Float

    init {
        mOriginalY = mBottomView.y
    }


    fun show() {
        val animator = ValueAnimator.ofFloat(mBottomView.y, mOriginalY)
        animator.duration = 400
        animator.interpolator = LinearOutSlowInInterpolator()
        animator.addUpdateListener { valueAnimator -> mBottomView.y = valueAnimator.animatedValue as Float }
        animator.start()
    }


    fun hide() {
        val animator = ValueAnimator.ofFloat(mBottomView.y, mOriginalY + mBottomView.height)
        animator.duration = 400
        animator.interpolator = LinearOutSlowInInterpolator()
        animator.addUpdateListener { valueAnimator -> mBottomView.y = valueAnimator.animatedValue as Float }
        animator.start()
    }
}
