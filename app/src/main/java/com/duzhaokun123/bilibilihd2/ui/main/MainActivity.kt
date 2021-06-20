package com.duzhaokun123.bilibilihd2.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.bases.BaseActivity
import com.duzhaokun123.bilibilihd2.databinding.ActivityMainBinding
import com.duzhaokun123.bilibilihd2.navigation.setupWithNavController
import com.duzhaokun123.bilibilihd2.ui.TestActivity
import com.duzhaokun123.bilibilihd2.ui.scape.UserScapeActivity
import com.duzhaokun123.bilibilihd2.ui.settings.SettingsActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.duzhaokun123.bilibilihd2.utils.ImageViewUtil.setBiliLevel

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
    Config.NO_TOOL_BAR,
    Config.LAYOUT_MATCH_HORI
) {
    private lateinit var navController: NavController
    private lateinit var headerView: View

    override fun findViews() {
        headerView = baseBinding.nv?.getHeaderView(0) ?: baseBinding.nrv?.headerView!!
    }

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        baseBinding.nv?.setupWithNavController(navController)
        baseBinding.nrv?.setupWithNavController(navController)
        val appBarConfiguration =
            AppBarConfiguration(navController.graph, baseBinding.dl)
        baseBinding.tb.setupWithNavController(navController, appBarConfiguration)
        (baseBinding.nv?.menu ?: baseBinding.nrv?.menu)!!.apply {
            findItem(R.id.item_settings)
                .setOnMenuItemClickListener {
                    startActivity<SettingsActivity>()
                    baseBinding.dl?.close()
                    true
                }
            findItem(R.id.item_test)
                .setOnMenuItemClickListener {
                    startActivity<TestActivity>()
                    baseBinding.dl?.close()
                    true
                }
        }
        baseBinding.dl?.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                reloadMyInfo()
            }
        })
        headerView.setOnClickListener {
            if (bilibiliClient.isLogin)
                UserScapeActivity.enter(this, bilibiliClient.loginResponse!!.userId)
        }
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = navController.currentDestination?.label
    }

    override fun onResume() {
        super.onResume()
        reloadMyInfo()
    }

    override fun onBackPressed() {
        if (baseBinding.dl != null && baseBinding.dl!!.isOpen)
            baseBinding.dl!!.close()
        else
            super.onBackPressed()
    }

    override fun initActionBar() = baseBinding.tb

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        insets.maxSystemBarsDisplayCutout.let {
            baseBinding.abl.updatePadding(left = it.left, right = it.right, top = it.top)
            baseBinding.fcv.updatePadding(left = it.left, right = it.right)
            (baseBinding.nv ?: baseBinding.nrv)?.updatePadding(left = it.left, top = it.top)
        }
    }

    @SuppressLint("SetTextI18n")
    fun reloadMyInfo() {
        if (bilibiliClient.isLogin)
            runIOCatchingResultRunMain(this, { bilibiliClient.appAPI.myInfo().await() }) { myInfo ->
                headerView.findViewById<TextView>(R.id.tv_name)?.apply {
                    text = myInfo.data.name
                    setTextColor(getColorCompat(if (myInfo.data.vip.status == 0) R.color.textColor else R.color.biliPink))
                }
                headerView.findViewById<TextView>(R.id.tv_coins)?.text =
                    "硬币: ${myInfo.data.coins}"
                headerView.findViewById<ImageView>(R.id.iv_level)?.setBiliLevel(myInfo.data.level)
                glideSafeLoadInto(myInfo.data.face, headerView.findViewById(R.id.civ_face))
            }
        else {
            headerView.findViewById<TextView>(R.id.tv_name)?.text = "未登录"
            headerView.findViewById<TextView>(R.id.tv_coins)?.text = "硬币: --"
            headerView.findViewById<ImageView>(R.id.iv_level)?.setBiliLevel(0)
            headerView.findViewById<ImageView>(R.id.civ_face).setImageDrawable(null)
        }
        if (baseBinding.nrv != null)
            headerView.findViewById<View>(R.id.civ_face).visibility =
                if (bilibiliClient.isLogin) View.VISIBLE else View.GONE
    }
}