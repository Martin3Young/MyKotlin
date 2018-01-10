package com.example.martin.mykotlin.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.widget.Toast
import com.example.martin.mykotlin.R
import com.example.martin.mykotlin.base.BaseActivity
import com.example.martin.mykotlin.bean.TabEntity
import com.example.martin.mykotlin.ui.fragment.HomeFragment
import com.example.martin.mykotlin.ui.fragment.MidFragment
import com.example.martin.mykotlin.ui.fragment.MyselfFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    
    private val mTitles = arrayOf("首页", "发现", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mMidFragment: MidFragment? = null
    private var mMyselfFragment: MyselfFragment? = null

    //默认为0
    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun start() {
    }

    private fun initTab(){
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mMidFragment?.let {
                transaction.show(it)
            } ?: MidFragment.getInstance(mTitles[position]).let {
                mMidFragment = it
                transaction.add(R.id.fl_container, it, "discovery") }
            2 //我的
            -> mMyselfFragment?.let {
                transaction.show(it)
            } ?: MyselfFragment.getInstance(mTitles[position]).let {
                mMyselfFragment = it
                transaction.add(R.id.fl_container, it, "mine") }

            else -> {

            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mMidFragment?.let { transaction.hide(it) }
        mMyselfFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }


    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this,"再次点击退出应用",Toast.LENGTH_LONG).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
