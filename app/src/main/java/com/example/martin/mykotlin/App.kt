package com.example.martin.mykotlin

import android.app.Application
import android.content.BroadcastReceiver

/**
 * Created by Martin on 2018/1/9.
 * @新浪微博: http://weibo.com/2603687001
 * @GitHub: https://github.com/Martin3Young
 * @CSDN: http://blog.csdn.net/qq_32346021
 * @简书: http://www.jianshu.com/u/6d64225b1910
 *
 */
 class App : Application(){

    private var mBroadcastReceiver : BroadcastReceiver ?= null

    override fun onCreate() {
        super.onCreate()
        initReceiver()
    }

    fun initReceiver(){

    }
}

