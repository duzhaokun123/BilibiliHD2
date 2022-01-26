package com.duzhaokun123.bilibilihd2.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.duzhaokun123.bilibilihd2.R
import com.duzhaokun123.bilibilihd2.databinding.ActivityMainBinding
import com.duzhaokun123.bilibilihd2.navigation.setupWithNavController
import com.duzhaokun123.bilibilihd2.ui.TestActivity
import com.duzhaokun123.bilibilihd2.ui.settings.SettingsActivity
import com.duzhaokun123.bilibilihd2.utils.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hiczp.bilibili.api.app.model.MyInfo
import io.github.duzhaokun123.androidapptemplate.bases.BaseActivity
import io.github.duzhaokun123.androidapptemplate.utils.launch
import io.github.duzhaokun123.androidapptemplate.utils.onFailure
import io.github.duzhaokun123.androidapptemplate.utils.onSuccess

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
    Config.LAYOUT_MATCH_HORI,
    Config.NO_BACK
) {

    private lateinit var navController: NavController

    override fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        baseBinding.nv?.setupWithNavController(navController)
        baseBinding.nrv?.setupWithNavController(navController)
        baseBinding.bnv?.setupWithNavController(navController)
        rootBinding.rootTb.setupWithNavController(navController,  AppBarConfiguration.Builder(navController.graph).build())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = navController.currentDestination?.label
    }

    override fun onApplyWindowInsetsCompat(insets: WindowInsetsCompat) {
        super.onApplyWindowInsetsCompat(insets)
        with(insets.maxSystemBarsDisplayCutout) {
            rootBinding.rootAbl.updatePadding(left = left, right = right)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val re = super.onCreateOptionsMenu(menu)
        menu ?: return re
        if (bilibiliClient.isLogin)
            menu.add(Menu.NONE, View.generateViewId(), 114514 ,"用户").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            io.github.duzhaokun123.androidapptemplate.utils.runIOCatching { bilibiliClient.appAPI.myInfo().await() }
                .onFailure { t ->
                    TipUtil.showTip(this@MainActivity, t.localizedMessage)
                    setLoginMenuItem(this@apply)
                }
                .onSuccess { r ->
                    setUserMenuItem(this@apply, r.data)
                }.launch()
            }
        else
            setLoginMenuItem(menu.add(Menu.NONE, View.generateViewId(), 114514 ,"登录"))
        return true
    }

    private fun setUserMenuItem(menuItem: MenuItem, data: MyInfo.Data) {
        glideSafeGet(data.face) { b ->
            menuItem.icon = RoundedBitmapDrawableFactory.create(resources, b).apply {
                isCircular = true
                setAntiAlias(true)
            }
            menuItem.setOnMenuItemClickListener {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setIcon(RoundedBitmapDrawableFactory.create(resources, b).apply {
                        isCircular = true
                        setAntiAlias(true)
                    })
                    .setTitle(data.name)
                    .setMessage("UID: ${data.mid}\n硬币: ${data.coins}\n${data.sign}")
                    .setNegativeButton("设置") { _, _ -> startActivity<SettingsActivity>()}
//                                .setNegativeButtonIcon(ResourcesCompat.getDrawable(resources, R.drawable.ic_settings, theme))
                    .setPositiveButton("test") { _, _ -> startActivity<TestActivity>() }
                    .setNeutralButton("Space") {_, _ -> BrowserUtil.openInApp(this@MainActivity, "bilibili://space/${data.mid}") }
                    .show()
                return@setOnMenuItemClickListener true
            }
        }
    }

    fun setLoginMenuItem(menuItem: MenuItem) {
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.setOnMenuItemClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("未登录或无效")
                .setMessage("前往 设置 -> 用户 -> 添加 以登录")
                .setNegativeButton("设置") { _, _ -> startActivity<SettingsActivity>()}
//                                .setNegativeButtonIcon(ResourcesCompat.getDrawable(resources, R.drawable.ic_settings, theme))
                .setPositiveButton("test") { _, _ -> startActivity<TestActivity>() }
                .show()
            return@setOnMenuItemClickListener true
        }
    }
}