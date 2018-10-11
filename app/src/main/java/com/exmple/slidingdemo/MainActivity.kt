package com.exmple.slidingdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exmple.slidingdemo.fourway.FourActivity
import com.exmple.slidingdemo.oneway.OneActivity
import com.exmple.slidingdemo.threeway.ThreeActivity
import com.exmple.slidingdemo.twoway.TwoActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @FileName: MainActivity.java
 * @author: villa_mou
 * @date: 10-13:59
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListner()
    }

    private fun initListner() {
        btn1.setOnClickListener {
            startActivity(Intent(this, OneActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this, TwoActivity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(this, ThreeActivity::class.java))
        }
        btn4.setOnClickListener {
            startActivity(Intent(this, FourActivity::class.java))
        }
    }
}