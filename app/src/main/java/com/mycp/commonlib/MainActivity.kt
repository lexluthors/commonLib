package com.mycp.commonlib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mycp.mylibrary.utils.AppUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppUtil.init(this)
    }
}