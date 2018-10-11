package com.exmple.slidingdemo.fourway;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.model.PositionData;

import java.util.List;

public class MyWrapPagerIndicator extends View implements IPagerIndicator {
    private int mVerticalPadding;
    private int mHorizontalPadding;
    private float mRoundRadius;
    private float mshadowRadius =  UIUtil.dip2px(getContext(),3f);
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private Interpolator mEndInterpolator = new LinearInterpolator();
    private List<PositionData> mPositionDataList;
    private Paint mPaint;
    private RectF mRect = new RectF();
    private boolean mRoundRadiusSet;
    private int starColor;
    private int endColor;

    public MyWrapPagerIndicator(Context context) {
        super(context);
        this.init(context);
    }

    private void init(Context context) {
        this.mPaint = new Paint(1);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mVerticalPadding = UIUtil.dip2px(context, 6.0D);
        this.mHorizontalPadding = UIUtil.dip2px(context, 30.0D);
        mPaint.setShadowLayer(mshadowRadius, UIUtil.dip2px(context,0f), UIUtil.dip2px(context,3f), Color.parseColor("#5531A99E"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, new int[]{starColor, endColor}, null, LinearGradient.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawRoundRect(this.mRect, this.mRoundRadius, this.mRoundRadius, this.mPaint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.mPositionDataList != null && !this.mPositionDataList.isEmpty()) {
            PositionData current = FragmentContainerHelper.getImitativePositionData(this.mPositionDataList, position);
            PositionData next = FragmentContainerHelper.getImitativePositionData(this.mPositionDataList, position + 1);
            this.mRect.left = (float) (current.mContentLeft - this.mHorizontalPadding) + (float) (next.mContentLeft - current.mContentLeft) * this.mEndInterpolator.getInterpolation(positionOffset);
            this.mRect.top = (float) (current.mContentTop - this.mVerticalPadding + mshadowRadius);
            this.mRect.right = (float) (current.mContentRight + this.mHorizontalPadding) + (float) (next.mContentRight - current.mContentRight) * this.mStartInterpolator.getInterpolation(positionOffset);
            this.mRect.bottom = (float) (current.mContentBottom + this.mVerticalPadding -  mshadowRadius);
            if (!this.mRoundRadiusSet) {
                this.mRoundRadius = (this.mRect.height()) / 2.0F;
            }
            this.invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPositionDataProvide(List<PositionData> dataList) {
        this.mPositionDataList = dataList;
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public int getVerticalPadding() {
        return this.mVerticalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.mVerticalPadding = verticalPadding;
    }

    public int getHorizontalPadding() {
        return this.mHorizontalPadding;
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.mHorizontalPadding = horizontalPadding;
    }


    public void setFillColor(int starColor, int endColor) {
        this.starColor = starColor;
        this.endColor = endColor;

    }

    public float getRoundRadius() {
        return this.mRoundRadius;
    }

    public void setRoundRadius(float roundRadius) {
        this.mRoundRadius = roundRadius;
        this.mRoundRadiusSet = true;
    }

    public Interpolator getStartInterpolator() {
        return this.mStartInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        this.mStartInterpolator = startInterpolator;
        if (this.mStartInterpolator == null) {
            this.mStartInterpolator = new LinearInterpolator();
        }

    }

    public Interpolator getEndInterpolator() {
        return this.mEndInterpolator;
    }

    public void setEndInterpolator(Interpolator endInterpolator) {
        this.mEndInterpolator = endInterpolator;
        if (this.mEndInterpolator == null) {
            this.mEndInterpolator = new LinearInterpolator();
        }

    }
}
