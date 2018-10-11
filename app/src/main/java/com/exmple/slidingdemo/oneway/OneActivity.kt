package com.exmple.slidingdemo.oneway

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exmple.slidingdemo.R
import kotlinx.android.synthetic.main.activity_one.*

/**
 * @FileName: OneActivity.java
 * @author: villa_mou
 * @date: 10-14:01
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        val data = arrayListOf<String>()
        for (i in 0..30) {
            data.add("")
        }
        rv.adapter = RecyclerviewAdapter(this, data)
    }
}