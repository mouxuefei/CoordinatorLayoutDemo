package com.exmple.slidingdemo.threeway

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exmple.slidingdemo.R
import com.exmple.slidingdemo.oneway.RecyclerviewAdapter
import kotlinx.android.synthetic.main.activity_three.*

/**
 * @FileName: OneActivity.java
 * @author: villa_mou
 * @date: 10-14:01
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class ThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        val data = arrayListOf<String>()
        for (i in 0..30) {
            data.add("")
        }
        recyclerView.adapter = RecyclerviewAdapter(this, data)
    }
}