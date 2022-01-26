package com.duzhaokun123.bilibilihd2.navigation

import android.os.Bundle
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigationrail.NavigationRailView
import java.lang.ref.WeakReference

/**
 * Sets up a [NavigationRailView] for use with a [NavController].
 *
 * The selected item in the NavigationView will automatically be updated when the destination
 * changes.
 */
fun NavigationRailView.setupWithNavController(
    navController: NavController
) {
    this.setOnItemSelectedListener { item ->
        NavigationUI.onNavDestinationSelected(item, navController)
    }
    val weakReference = WeakReference(this)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                view.menu.forEach { item ->
                    if (NavigationUI.matchDestination(destination, item.itemId)) {
                        item.isChecked = true
                    }
                }
            }
        })
}