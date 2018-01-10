package com.example.martin.mykotlin.ui.fragment

import android.os.Bundle
import com.example.martin.mykotlin.R
import com.example.martin.mykotlin.base.BaseFragment

/**
 * Created by Martin on 2018/1/9.
 * @新浪微博: http://weibo.com/2603687001
 * @GitHub: https://github.com/Martin3Young
 * @CSDN: http://blog.csdn.net/qq_32346021
 * @简书: http://www.jianshu.com/u/6d64225b1910
 *
 */
class HomeFragment : BaseFragment(){

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }

}